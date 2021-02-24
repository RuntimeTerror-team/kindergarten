package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.ApplicationDto;
import lt.vtmc.kindergarten.dto.ApplicationInfoDto;
import lt.vtmc.kindergarten.dto.PersonDto;

import lt.vtmc.kindergarten.service.exceptions.QueueDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;


import java.time.LocalDate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UserService userService;

    @Autowired
    private QueueDao queueDao;

    @Autowired
    private KindergartenApplicationFormService kindergartenApplicationService;

    @Autowired
    private UserDao userDao;


    @Transactional
    public void addApplication(@Valid ApplicationCreationDto applicationCreationDto) {
        Queue queue = queueDao.findByStatus(QueueStatusEnum.ACTIVE);

        if (queue != null) {

            Person child = personDao.getOne(applicationCreationDto.getChildId());
            Person firstParent = personDao.getOne(applicationCreationDto.getFirstParentId());

            Person secondParent = null;
            boolean isSecondParent = false;
            if (applicationCreationDto.getSecondParentId() != null) {

                isSecondParent = true;
                secondParent = personDao.getOne(applicationCreationDto.getSecondParentId());

            }

            if (secondParent != null) {
                createParentUser(secondParent.getFirstName(), secondParent.getLastName());

            }

            Application application = applicationDao.findApplicationByChild(child);

            if (application == null) {
                application = new Application();

            } else {
                throw new RuntimeException("Application already exists");
            }


            application.setDate(java.sql.Date.valueOf(LocalDate.now()));

            application.setIsAdopted(applicationCreationDto.isAdopted());
            application.setIsMultiChild(applicationCreationDto.isMultiChild());
            application.setIsGuardianStudent(applicationCreationDto.isGuardianStudent());
            application.setIsGuardianDisabled(applicationCreationDto.isGuardianDisabled());

            if (child.getCity() == CityEnum.VILNIUS) {
                application.setScore(countScore(applicationCreationDto) + 10);
            } else {
                application.setScore(countScore(applicationCreationDto));
            }

            application.setChild(child);
            application.setParent(firstParent);
            if (isSecondParent) {
                application.setSecondParent(secondParent);
            }

            application.setQueue(queue);

            application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);

            application.setKindergartenApplicationForms(parseKindergartenApplications(applicationCreationDto, application));

            applicationDao.save(application);
        } else {
            throw new QueueDoesntExistException("Active queue must exists");
        }
    }

    @Transactional
    public List<ApplicationDto> getApplicationsList() {
        List<Application> applications = applicationDao.findAll();
        return applications.stream().map(application -> new ApplicationDto(application.getId(), application.getApplicationStatus(),
                application.getChild(), new PersonDto(application.getParent()),
                application.getScore(), application.getSecondParent(), application.getDate(), application.isAdopted(),
                application.isMultiChild(), application.isGuardianDisabled(), application.isGuardianStudent(),
                application.getKindergartenApplicationForms())).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public ApplicationCreationDto getApplication(Long id) {
        Application application = applicationDao.getOne(id);
        return new ApplicationCreationDto(application);
    }

    @Transactional
    public List<ApplicationCreationDto> getApplications() {
        List<Application> applications = applicationDao.findAll(Sort.by(Sort.Direction.ASC, "date"));
        List<ApplicationCreationDto> applicationList = applications.stream().map(application -> new ApplicationCreationDto(application))
                .collect(Collectors.toList());
        return applicationList;
    }

    @Transactional
    public List<ApplicationInfoDto> getApplicationsInfo(String username) {
        User user = userDao.findByUsername(username);
        Person parent = personDao.findByUser(user);

        Set<Application> applicationsByParent = applicationDao.findByParent(parent);

        List<ApplicationInfoDto> applicationInfoList = applicationsByParent.stream().map(application -> new ApplicationInfoDto(application)
        ).collect(Collectors.toList());

        return applicationInfoList;
    }


    private int countScore(ApplicationCreationDto applicationCreationDto) {
        int sumOfPriorities = 0;

        if (applicationCreationDto.isAdopted()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isGuardianDisabled()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isAdopted()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        if (applicationCreationDto.isGuardianStudent()) {
            sumOfPriorities = sumOfPriorities + 1;
        }
        return sumOfPriorities;
    }

    @Transactional
    public void updateApplication(Long id, ApplicationCreationDto applicationCreationDto) {
        Queue queue = queueDao.findByStatus(QueueStatusEnum.ACTIVE);

        if (queue != null) {
            Application application = applicationDao.getOne(id);

            Person child = personDao.getOne(applicationCreationDto.getChildId());
            Person firstParent = personDao.getOne(applicationCreationDto.getFirstParentId());
            Person secondParent = personDao.getOne(applicationCreationDto.getSecondParentId());

            application.setDate(applicationCreationDto.getDate());
            application.setIsAdopted(applicationCreationDto.isAdopted());
            application.setIsMultiChild(applicationCreationDto.isMultiChild());
            application.setIsGuardianStudent(applicationCreationDto.isGuardianDisabled());
            application.setIsGuardianDisabled(applicationCreationDto.isGuardianDisabled());

            if (child.getCity() == CityEnum.VILNIUS) {
                application.setScore(countScore(applicationCreationDto) + 10);
            } else {
                application.setScore(countScore(applicationCreationDto));
            }

            application.setChild(child);
            application.setParent(firstParent);
            application.setSecondParent(secondParent);

            application.setQueue(queue);

            application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);

            //Delete preexisting applications before applying new ones
            kindergartenApplicationService.deleteApplicationFormsByApplicationId(application.getId());

            Set<KindergartenApplicationForm> kindergartenApplicationForms = parseKindergartenApplications(applicationCreationDto, application);
            application.setKindergartenApplicationForms(kindergartenApplicationForms);

            applicationDao.save(application);
        } else {
            throw new QueueDoesntExistException("Active queue must exists");
        }

    }

    /**
     * Creates applications to concrete kindergartens
     *
     * @param applicationCreationDto dto that contains Map<Integer priority, Long kindergartenId>
     * @param application            application to which new KindergartenApplicationForms will be created
     * @return Set of application forms to specific kindergartens
     */
    private Set<KindergartenApplicationForm> parseKindergartenApplications(ApplicationCreationDto applicationCreationDto, Application application) {
        Map<Integer, Long> applicationMetadata = applicationCreationDto.getPriorityForKindergartenID();

        Set<KindergartenApplicationForm> kindergartenApplications = applicationMetadata.entrySet().stream().map(entry -> {
            Kindergarten kindergarten = kindergartenDao.getOne(entry.getValue());
            removeApplicationFormsFromApplication(application);
            removeApplicationFormsFromKindergarten(kindergarten, entry.getValue());

            KindergartenApplicationForm kindergartenApplicationForm = new KindergartenApplicationForm();
            kindergartenApplicationForm.setKindergarten(kindergarten);
            kindergartenApplicationForm.setPriority(entry.getKey());
            kindergartenApplicationForm.setAccepted(false);
            kindergartenApplicationForm.setApplication(application);

            return kindergartenApplicationForm;

        }).collect(Collectors.toSet());

        return kindergartenApplications;

    }

    private void removeApplicationFormsFromApplication(Application application) {
        application
                .setKindergartenApplicationForms(
                        application.getKindergartenApplicationForms()
                                .stream()
                                .filter(item -> item.getApplication().getId() != application.getId())
                                .collect(Collectors.toSet()));
    }

    private void removeApplicationFormsFromKindergarten(Kindergarten kindergarten, Long kindergartenIdToRemove) {
        kindergarten
                .setApplicationsSet(
                        kindergarten.getApplicationsSet()
                                .stream()
                                .filter(item -> item.getKindergarten().getId() != kindergartenIdToRemove)
                                .collect(Collectors.toSet()));
    }

    public void createParentUser(String firstName, String lastName) {
        userService.createGuardian(firstName, lastName);
    }










//TODO UZBAIGTI SU SITUO REIKALIUKUS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//TODO UZBAIGTI SU SITUO REIKALIUKUS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// TODO UZBAIGTI SU SITUO REIKALIUKUS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//
//    @Transactional
//    public List<ApplicationCreationDto> getSortedApplications() {
//        List<Application> applications = applicationDao.findByApplicationStatus(ApplicationStatusEnum.WAITING);
//
//        applications.sort(Comparator.comparing(Application::getScore).thenComparing((o1, o2) -> {
//            int age1 = countChildAge(o1.getChild().getPersonalCode());
//            int age2 = countChildAge(o2.getChild().getPersonalCode());
//            if (age1 == age2) {
//                return o1.getChild().getLastName().compareTo(o2.getChild().getLastName());
//            } else {
//                if (age1 > age2) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        }));
//
//        List<ApplicationCreationDto> applicationList = applications
//                .stream()
//                .map(application -> new ApplicationCreationDto(application))
//                .collect(Collectors.toList());
//
//        return applicationList;
//    }
//
//
////    static class compareByAgeThenChildLastName implements Comparator<Application> {
////        @Override
////        public int compare(Application o1, Application o2) {
////            int age1 = countChildAge(o1.getChild().getPersonalCode());
////            int age2 = countChildAge(o2.getChild().getPersonalCode());
////
////            if (age1 == age2) {
////                return o1.getChild().getLastName().compareTo(o2.getChild().getLastName());
////            } else {
////                if (age1 > age2) {
////                    return 1;
////                } else {
////                    return -1;
////                }
////            }
////        }
////    }
//
//
//    public static int countChildAge(String personalCode) {
//        int birthdayYear = Integer.parseInt(personalCode.substring(1, 3));
//        LocalDate localDate = LocalDate.now();
//        int currentYear = localDate.getYear() - 2000;
//        return currentYear - birthdayYear;
//    }

}

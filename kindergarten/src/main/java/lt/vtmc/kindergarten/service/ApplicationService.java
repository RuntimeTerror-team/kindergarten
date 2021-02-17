package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.ApplicationDto;
import lt.vtmc.kindergarten.dto.PersonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;


import java.time.LocalDate;

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
                application.setScore(countScore(applicationCreationDto) + 1);
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
                application.setScore(countScore(applicationCreationDto) + 1);
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


}

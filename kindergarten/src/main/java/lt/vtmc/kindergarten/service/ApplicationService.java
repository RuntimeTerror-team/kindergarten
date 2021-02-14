package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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

    @Transactional
    public void addApplication(@Valid ApplicationCreationDto applicationCreationDto) {
        Person child = personDao.getOne(applicationCreationDto.getChildId());
        Person firstParent = personDao.getOne(applicationCreationDto.getFirstParentId());
        Person secondParent = personDao.getOne(applicationCreationDto.getSecondParentId());
        if ( secondParent != null ){
            createParentUser(secondParent.getFirstName(), secondParent.getLastName());
        }

        Application application = new Application();
        application.setDate(applicationCreationDto.getDate());

        application.setAdopted(applicationCreationDto.isAdopted());
        application.setMultiChild(applicationCreationDto.isMultiChild());
        application.setGuardianStudent(applicationCreationDto.isGuardianDisabled());
        application.setGuardianDisabled(applicationCreationDto.isGuardianDisabled());

        if (child.getCity() == CityEnum.VILNIUS) {
            application.setScore(countScore(applicationCreationDto) + 1);
        } else {
            application.setScore(countScore(applicationCreationDto));
        }

        application.setChildId(child);
        application.setParentId(firstParent);
        application.setSecondParentId(secondParent);

        application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);

        application.setKindergartenApplicationForms(parseKindergartenApplications(applicationCreationDto, application));

        applicationDao.save(application);
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


    private Set<KindergartenApplicationForm> parseKindergartenApplications(ApplicationCreationDto applicationCreationDto, Application application) {
        Map<Integer, Long> applicationMetadata = applicationCreationDto.getPriorityForKindergartenID();

        Set<KindergartenApplicationForm> kindergartenApplications = applicationMetadata.entrySet().stream().map(entry -> {
            Kindergarten kindergarten = kindergartenDao.getOne(entry.getValue());
            KindergartenApplicationForm kindergartenApplicationForm = new KindergartenApplicationForm();
            kindergartenApplicationForm.setKindergarten(kindergarten);
            kindergartenApplicationForm.setPriority(entry.getKey());
            kindergartenApplicationForm.setAccepted(false);
            kindergartenApplicationForm.setApplication(application);

            return kindergartenApplicationForm;

        }).collect(Collectors.toSet());

        return kindergartenApplications;

    }


    @Transactional
    public void updateApplication(Long id, ApplicationCreationDto applicationCreationDto){
        Application application = applicationDao.getOne(id);
    //TODO fill this part

        applicationDao.save(application);

    }

    public void createParentUser(String firstName, String lastName){
        userService.createGuardian(firstName,lastName);
    }


//    @Transactional(readOnly = true)
//    public ApplicationCreationDto getApplication(Long id){
//        Application application = applicationDao.getOne(id);
//        return new ApplicationCreationDto(application);
//    }
//
//    @Transactional
//    public List<ApplicationCreationDto> getApplications(){
//        List<Application> applications = applicationDao.findAll();
//        List<ApplicationCreationDto> applicationList = applications.stream()
//                .map(application -> new ApplicationCreationDto(application))
//                .collect(Collectors.toList());
//        return applicationList;
//    }


}

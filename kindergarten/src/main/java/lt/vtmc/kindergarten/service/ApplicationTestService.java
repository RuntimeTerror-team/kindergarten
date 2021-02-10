//package lt.vtmc.kindergarten.service;
//
//import lt.vtmc.kindergarten.dao.*;
//import lt.vtmc.kindergarten.domain.*;
//import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.Valid;
//
//@Service
//@Validated
//public class ApplicationTestService {
//    @Autowired
//    private ApplicationTestDao applicationTestDao;
//
//    @Autowired
//    private KindergartenDao kindergartenDao;
//
//    @Autowired
//    private PersonTestDao personTestDao;
//
//    @Transactional
//    public void addApplication(@Valid ApplicationCreationDto applicationCreationDto) {
//        Child child = childDao.getOne(applicationCreationDto.getChildId());
//        User user = userDao.getOne(applicationCreationDto.getUsername());
//        Person secondParent = personDao.getOne(applicationCreationDto.getSecondParentId());
//
//        Application application = new Application();
//        application.setDate(applicationCreationDto.getDate());
//
//        application.setAdopted(applicationCreationDto.isAdopted());
//        application.setMultiChild(applicationCreationDto.isMultiChild());
//        application.setGuardianStudent(applicationCreationDto.isGuardianDisabled());
//        application.setGuardianDisabled(applicationCreationDto.isGuardianDisabled());
//
//        if (child.getCity() == CityEnum.VILNIUS) {
//            application.setScore(countScore(applicationCreationDto) + 1);
//        } else {
//            application.setScore(countScore(applicationCreationDto));
//        }
//
//        application.setChild(child);
//        application.setSecondParentInfo(secondParent);
//        application.setApplicationStatus(ApplicationStatusEnum.SUBMITTED);
//        application.setApplicant(user);
//        application.setKindergartenApplicationForms(parseKindergartenApplications(applicationCreationDto, application));
//        user.addUserApplication(application);
//
//        applicationDao.save(application);
//    }
//
//    private int countScore(ApplicationCreationDto applicationCreationDto) {
//        int sumOfPriorities = 0;
//
//        if (applicationCreationDto.isAdopted()) {
//            sumOfPriorities = sumOfPriorities + 1;
//        }
//        if (applicationCreationDto.isGuardianDisabled()) {
//            sumOfPriorities = sumOfPriorities + 1;
//        }
//        if (applicationCreationDto.isAdopted()) {
//            sumOfPriorities = sumOfPriorities + 1;
//        }
//        if (applicationCreationDto.isGuardianStudent()) {
//            sumOfPriorities = sumOfPriorities + 1;
//        }
//        return sumOfPriorities;
//    }
//}

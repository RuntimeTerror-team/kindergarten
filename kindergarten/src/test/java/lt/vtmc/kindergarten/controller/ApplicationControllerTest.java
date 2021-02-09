//package lt.vtmc.kindergarten.controller;
//
//import lt.vtmc.kindergarten.dao.*;
//import lt.vtmc.kindergarten.domain.*;
//import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@SpringBootTest
//@DisplayName("When running Application controller")
//public class ApplicationControllerTest {
//
//    @Autowired
//    private ApplicationController applicationController;
//
//    @Autowired
//    private ChildDao childDao;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private KindergartenDao kindergartenDao;
//
//    @Autowired
//    private DistrictDao districtDao;
//
//    @Autowired
//    private ChildController childController;
//
//    @Autowired
//    private ApplicationDao applicationDao;
//
//    @Autowired
//    private PersonDao personDao;
//
//    @Test
//    @DisplayName("create an application")
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    void testCreatingApplication() {
//        District district = KindergartenTestUtil.createDistrict();
//        districtDao.save(district);
//
//        Kindergarten kindergarten = KindergartenTestUtil.createKindergarten();
//        kindergarten.setDistrict(district);
//        kindergartenDao.save(kindergarten);
//
//        Child child = new Child();
//        child.setFirstName("Antanas");
//        child.setLastName("Antanaitis");
//        child.setPersonalCode("49004180333");
//        child.setStreetAddress("Kanklių g.4");
//        child.setCity(CityEnum.VILNIUS);
//        childDao.save(child);
//
//        Person secondParent = new Person();
//        secondParent.setFirstName("Alvydas");
//        secondParent.setLastName("Antanaitis");
//        secondParent.setEmail("alvydasantanaitis@gmail.com");
//        secondParent.setPhoneNumber("862403633");
//        secondParent.setPersonalCode("3900418333");
//        secondParent.setAddress("Kanklių g. 4");
//        secondParent.setCity(CityEnum.VILNIUS);
//        personDao.save(secondParent);
//
//        User user = new User();
//        user.setUsername("hhhwwwttt");
//        user.setPersonalCode("12345678910");
//        user.setRole(new Role(RoleType.GUARDIAN));
//        user.setFirstName("Petras");
//        user.setLastName("Poskauskas");
//        user.setPassword("kompiuteriailabaismagu");
//
//        ApplicationCreationDto applicationCreationDto = new ApplicationCreationDto();
//        applicationCreationDto.setDate(new Date());
//        applicationCreationDto.setAdopted(true);
//        applicationCreationDto.setGuardianDisabled(false);
//        applicationCreationDto.setMultiChild(false);
//        applicationCreationDto.setGuardianStudent(false);
//        applicationCreationDto.setScore(2);
//        applicationCreationDto.setChildId(child.getId());
//        applicationCreationDto.setUsername("hhhwwwttt");
//
//
//        Map<Integer,Long> priorityKindergarten = new HashMap<>();
//        priorityKindergarten.put(1,kindergarten.getId());
//
//
//        applicationCreationDto.setPriorityForKindergartenID(priorityKindergarten);
//        applicationCreationDto.setUsername(user.getUsername());
//
//        userDao.save(user);
//
//        applicationController.addApplication(applicationCreationDto);
//
//        applicationController.getApplications("hhhwwwttt");
//
////        assertEquals(1, applicationController.getApplications()???, "should create application");
//
//    }
//
//
//
//}

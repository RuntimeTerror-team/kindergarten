package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.ChildDao;
import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running Application Controller")
public class ApplicationControllerTest {

    @Autowired
    private ApplicationController applicationController;

    @Autowired
    private ChildDao childDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private ChildController childController;

    @Test
    @DisplayName("create an application")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testCreatingApplication() {
        District district = new District();
        district.setId(1L);
        district.setTitle("Antakalnis");
        districtDao.save(district);

        Kindergarten kindergarten = new Kindergarten();
        kindergarten.setTitle("Pusaite");
        kindergarten.setAddress("Gatves g. 56");
        kindergarten.setCity(CityEnum.VILNIUS);
        kindergarten.setPostalCode("12546");
        kindergarten.setPhoneNumber("862403523");
        kindergarten.setEmail("darzeliasass@gmail.com");
        kindergarten.setWebsite("www.darzelis.lt");
        kindergarten.setCompanyCode("19555587");
        kindergarten.setDistrict(district);
        kindergartenDao.save(kindergarten);

        Child child = new Child();
        child.setLastName("Antanas");
        child.setLastName("Antanaitis");
        child.setPersonalCode(49004180333L);
        child.setStreetAddress("Kankliu g.4");
        child.setCity(CityEnum.VILNIUS);
        childDao.save(child);

        ApplicationCreationDto applicationCreationDto = new ApplicationCreationDto();
        applicationCreationDto.setAdopted(true);
        applicationCreationDto.setDate(new Date());
        applicationCreationDto.setGuardianDisabled(false);
        applicationCreationDto.setMultiChild(false);
        applicationCreationDto.setScore(0);
        applicationCreationDto.setChildId(child.getId());


        User user = new User();
        user.setUsername("hhhwwwttt");
        user.setPersonalCode(123456789L);
        user.setRole(new Role(RoleType.GUARDIAN));
        user.setFirstName("Petras");
        user.setLastName("Poskauskas");
        user.setPassword("kompiuteriailabaismagu");


        UserApplication userApplication = new UserApplication();
        userApplication.setUser(user);

        Set<UserApplication> userApplications = new HashSet<>();
        userApplications.add(userApplication);

        user.setUserApplication(userApplications);

        Map<Integer,Long> priorityKindergarten = new HashMap<>();
        priorityKindergarten.put(1,kindergarten.getId());


        applicationCreationDto.setPriorityKindergarten(priorityKindergarten);
        applicationCreationDto.setUsername(user.getUsername());



        applicationController.addApplication(applicationCreationDto);


    }



}

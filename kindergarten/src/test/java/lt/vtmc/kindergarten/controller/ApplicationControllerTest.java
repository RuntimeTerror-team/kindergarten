package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running Application controller")
public class ApplicationControllerTest {

    @Autowired
    private ApplicationController applicationController;

    @Autowired
    private UserDao userDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private PersonDao personDao;

    @Test
    @DisplayName("create an application")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Transactional
    void testCreatingApplication() {
        District district = KindergartenTestUtil.createDistrict();
        districtDao.save(district);

        Kindergarten kindergarten = KindergartenTestUtil.createKindergarten();
        kindergarten.setDistrict(district);
        kindergartenDao.save(kindergarten);

        Person child = new Person();
        child.setFirstName("Antanas");
        child.setLastName("Antanaitis");
        child.setPersonalCode("13345678910");
        child.setAddress("Kanklių g.4");
        child.setCity(CityEnum.VILNIUS);
        child.setPostalCode("12355");
        personDao.save(child);

        Person secondParent = new Person();
        secondParent.setFirstName("Alvydas");
        secondParent.setEmail("alvydasantanaitis@gmail.com");
        secondParent.setLastName("Antanaitis");
        secondParent.setPhoneNumber("862403633");
        secondParent.setPersonalCode("12545678910");
        secondParent.setAddress("Kanklių g. 4");
        secondParent.setCity(CityEnum.VILNIUS);
        secondParent.setPostalCode("12345");
        personDao.save(secondParent);


        Person firstParent = new Person();
        firstParent.setFirstName("Alvydasdas");
        firstParent.setLastName("Antanasdaitis");
        firstParent.setEmail("alvydasanasdtanaitis@gmail.com");
        firstParent.setPhoneNumber("862443633");
        firstParent.setPersonalCode("12345678910");
        firstParent.setAddress("Kankfių g. 4");
        firstParent.setCity(CityEnum.VILNIUS);
        firstParent.setPostalCode("66666");
        personDao.save(firstParent);


        ApplicationCreationDto applicationCreationDto = new ApplicationCreationDto();
        applicationCreationDto.setDate(new Date());
        applicationCreationDto.setAdopted(true);
        applicationCreationDto.setGuardianDisabled(false);
        applicationCreationDto.setMultiChild(false);
        applicationCreationDto.setGuardianStudent(false);
        applicationCreationDto.setChildId(child.getId());
        applicationCreationDto.setFirstParentId(firstParent.getId());
        applicationCreationDto.setSecondParentId(secondParent.getId());



        Map<Integer,Long> priorityKindergarten = new HashMap<>();
        priorityKindergarten.put(1,kindergarten.getId());

        applicationCreationDto.setPriorityForKindergartenID(priorityKindergarten);

        applicationController.addApplication(applicationCreationDto);
        applicationController.addApplication(applicationCreationDto);
//        applicationController.getApplications("hhhwwwttt");

//        assertEquals(1, applicationController.getApplications()???, "should create application");

    }



}

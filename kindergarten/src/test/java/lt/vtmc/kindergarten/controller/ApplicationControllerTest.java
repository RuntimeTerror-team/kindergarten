package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SpringBootTest
@DisplayName("When running Application Controller")
public class ApplicationControllerTest {

    @Autowired
    private ApplicationController applicationController;



    @Autowired
    private ChildController childController;

    @Test
    @DisplayName("create an application")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreatingApplication() {
//        Role defaultRole = new Role();
//        defaultRole.setType(RoleType.GUARDIAN);
//
//        User mother = new User();
//        mother.setRole(defaultRole);
//        mother.setFirstName("Ona");
//        mother.setLastName("Skrebutienė");
//        mother.setPersonalCode(46304280323L);
//        //nera likusiu lauku - suziureti dar
//
//        User father = new User();
//        father.setRole(defaultRole);
//        father.setFirstName("Jonas");
//        father.setLastName("Skrebutis");
//        father.setPersonalCode(37304280312L);
//        //nera likusiu lauku - suziureti dar
//
//        Set<UserApplication> users = new HashSet<>();
//
//
//        Child child = new Child();
//        child.setId(1L);
//        child.setFirstName("Lukas");
//        child.setLastName("Skrebutis");
//        child.setPersonalCode(39004280333L);
//        child.setStreetAddress("Kanklių g. 35");
//        child.setCity(CityEnum.VILNIUS);
//
//        ApplicationDto applicationDto = new ApplicationDto();
//
//        applicationDto.setId(1L);
//        applicationDto.setAdopted(true);
//        applicationDto.setMultiChild(true);
//        applicationDto.setGuardianDisabled(false);
//        applicationDto.setGuardianStudent(false);
//        applicationDto.setDate(new Date());
//        applicationDto.setChild(child);
//
//        applicationDto.setUsers(users);
//        //dar kindergartens sudeti

    }



}

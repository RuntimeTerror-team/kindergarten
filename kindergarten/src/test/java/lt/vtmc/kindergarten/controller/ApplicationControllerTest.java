package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.TestUtils;
import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.QueueDtoWithOpeningDate;
import lt.vtmc.kindergarten.service.QueueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@WithMockUser(username="AndriusValatka1",roles={"GUARDIAN", "EDUCATION_SPECIALIST"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running Application controller")
public class ApplicationControllerTest {

    @Autowired
    private ApplicationController applicationController;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private QueueService queueService;


    @Test
    @DisplayName("create an application")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Transactional
    void testCreatingApplication() {
        createDefaultQueueDistrictKindergarten();

        createFilledApplicationWithOneKindergartenAndThreePersons();

        assertTrue(applicationDao.findAll().size() == 1);
    }

    @Test
    @DisplayName("update an application")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Transactional
    public void getDistributedApplicationsInfoTest(){

    }


    @Test
    @DisplayName("get all applications")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Transactional
    void testGetApplications() {

        createDefaultQueueDistrictKindergarten();

        //The first application creation
        createFilledApplicationWithOneKindergartenAndThreePersons();

        //The second application creation
        createDefaultChildFirstAndSecondParent("49041111111", "49041222222", "49041333333");

        ApplicationCreationDto applicationCreationDto2 = TestUtils.createDefaultApplicationDto();
        applicationCreationDto2.setChildId(personDao.findByPersonalCode("49041111111").getId());
        applicationCreationDto2.setFirstParentId(personDao.findByPersonalCode("49041222222").getId());
        applicationCreationDto2.setSecondParentId(personDao.findByPersonalCode("49041333333").getId());

        Map<Integer, Long> priorityKindergarten2 = new HashMap<>();
        Kindergarten kindergarten2 = kindergartenDao.getOne(3L);
        priorityKindergarten2.put(1, kindergarten2.getId());

        applicationCreationDto2.setPriorityForKindergartenID(priorityKindergarten2);

        applicationController.addApplication(applicationCreationDto2);

        assertEquals(2, applicationDao.findAll().size(), "Should get all applications");
    }

    private void createDefaultQueueDistrictKindergarten() {
        QueueDtoWithOpeningDate queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);

        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtDao.save(district);

        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("11111111");
        kindergarten.setDistrict(district);
        kindergartenDao.save(kindergarten);
    }

    private void createDefaultChildFirstAndSecondParent(String childPersonalCode, String firstParentPersonalCode, String secondParentPersonalCode) {
        Person child = TestUtils.createDefaultPerson(childPersonalCode, "Antanas", "Antanaitis");
        personDao.save(child);

        Person firstParent = TestUtils.createDefaultPerson(firstParentPersonalCode,  "Antanas", "Antanaitis");
        personDao.save(firstParent);

        Person secondParent = TestUtils.createDefaultPerson(secondParentPersonalCode, "Antanas", "Antanaitis");
        personDao.save(secondParent);
    }

    private void createFilledApplicationWithOneKindergartenAndThreePersons() {

        createDefaultChildFirstAndSecondParent("49041888888", "49041777777", "49041666666");
        ApplicationCreationDto applicationCreationDto = TestUtils.createDefaultApplicationDto();

        applicationCreationDto.setChildId(personDao.findByPersonalCode("49041888888").getId());
        applicationCreationDto.setFirstParentId(personDao.findByPersonalCode("49041777777").getId());
        applicationCreationDto.setSecondParentId(personDao.findByPersonalCode("49041666666").getId());

        Map<Integer, Long> priorityKindergarten = new HashMap<>();
        Kindergarten kindergarten = kindergartenDao.getOne(3L);
        priorityKindergarten.put(1, kindergarten.getId());

        applicationCreationDto.setPriorityForKindergartenID(priorityKindergarten);
        applicationController.addApplication(applicationCreationDto);
    }
}

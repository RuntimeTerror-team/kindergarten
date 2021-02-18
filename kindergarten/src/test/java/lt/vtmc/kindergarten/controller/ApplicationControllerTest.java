package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.TestUtils;
import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.ApplicationCreationDto;
import lt.vtmc.kindergarten.dto.QueueDtoFromAdmin;
import lt.vtmc.kindergarten.service.QueueService;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Autowired
    private KindergartenApplicationFormDao kindergartenApplicationFormDao;



    @Test
    @DisplayName("create an application")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Transactional
    void testCreatingApplication() {
        QueueDtoFromAdmin queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);

        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtDao.save(district);

        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("123456877");
        kindergarten.setDistrict(district);
        kindergartenDao.save(kindergarten);

        Person child = TestUtils.createDefaultPerson("13345678910");
        personDao.save(child);

        Person secondParent = TestUtils.createDefaultPerson("12545678910");
        personDao.save(secondParent);

        Person firstParent = TestUtils.createDefaultPerson("12345678910");
        personDao.save(firstParent);

        ApplicationCreationDto applicationCreationDto = TestUtils.createDefaultApplicationDto();
        applicationCreationDto.setChildId(child.getId());
        applicationCreationDto.setFirstParentId(firstParent.getId());
        applicationCreationDto.setSecondParentId(secondParent.getId());

        Map<Integer, Long> priorityKindergarten = new HashMap<>();
        priorityKindergarten.put(1, kindergarten.getId());

        applicationCreationDto.setPriorityForKindergartenID(priorityKindergarten);
        applicationController.addApplication(applicationCreationDto);

        assertTrue(applicationDao.findAll().size() == 1);
    }


    @Test
    @DisplayName("update an application")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Transactional
    void testUpdateApplication() {

        QueueDtoFromAdmin queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);

        District district = TestUtils.createDefaultDistrict("");
        districtDao.save(district);

        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("11111111");
        kindergarten.setDistrict(district);
        kindergartenDao.save(kindergarten);

        District district2 = TestUtils.createDefaultDistrict("KATAKALNIS");
        districtDao.save(district2);

        Kindergarten kindergarten2 = TestUtils.createDefaultKindergarten("22222222");
        kindergarten2.setDistrict(district2);
        kindergartenDao.save(kindergarten2);

        Kindergarten kindergarten3 = TestUtils.createDefaultKindergarten("33333333");
        kindergarten2.setDistrict(district2);
        kindergartenDao.save(kindergarten3);


        Person child = TestUtils.createDefaultPerson("13345678910");
        personDao.save(child);

        Person secondParent = TestUtils.createDefaultPerson("12545678910");
        personDao.save(secondParent);


        Person firstParent = TestUtils.createDefaultPerson("12345678910");
        personDao.save(firstParent);


        ApplicationCreationDto applicationCreationDto = TestUtils.createDefaultApplicationDto();
        applicationCreationDto.setChildId(child.getId());
        applicationCreationDto.setFirstParentId(firstParent.getId());
        applicationCreationDto.setSecondParentId(secondParent.getId());

        Map<Integer, Long> priorityKindergarten = new HashMap<>();
        priorityKindergarten.put(1, kindergarten.getId());

        applicationCreationDto.setPriorityForKindergartenID(priorityKindergarten);

        applicationController.addApplication(applicationCreationDto);
        assertTrue(kindergartenApplicationFormDao.findAll().size() == 1);


        Application application = applicationDao.findAll().get(0);


        ApplicationCreationDto applicationUpdateDto = TestUtils.createDefaultApplicationDto();
        applicationUpdateDto.setChildId(child.getId());
        applicationUpdateDto.setFirstParentId(firstParent.getId());
        applicationUpdateDto.setSecondParentId(secondParent.getId());

        Map<Integer, Long> priorityKindergartenUpdate = new HashMap<>();
        priorityKindergartenUpdate.put(1, kindergarten.getId());
        priorityKindergartenUpdate.put(2, kindergarten2.getId());
        priorityKindergartenUpdate.put(3, kindergarten2.getId());

        applicationUpdateDto.setPriorityForKindergartenID(priorityKindergartenUpdate);
        applicationController.updateApplication(application.getId(), applicationUpdateDto);

        assertTrue(kindergartenApplicationFormDao.findAll().size() == 3);

        assertTrue(kindergartenDao.findByCompanyCode("11111111").getApplicationsSet().size() == 1, "Should apply to kindergarten once");
        assertTrue(kindergartenDao.findByCompanyCode("22222222").getApplicationsSet().size() == 1, "Should not apply to the same kindergarten twice");
        assertTrue(kindergartenDao.findByCompanyCode("33333333").getApplicationsSet().size() == 0, "Should have no applications");
        assertTrue(applicationDao.findApplicationByChild(personDao.findByPersonalCode("13345678910")).getKindergartenApplicationForms().size() == 3);
//        applicationDao.findAll();
    }


    @Test
    @DisplayName("get all applications")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Transactional
    void testGetApplications(){


        QueueDtoFromAdmin queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);

        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtDao.save(district);

        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("123456877");
        kindergarten.setDistrict(district);
        kindergartenDao.save(kindergarten);

        //PIRMAS PRASYMAS

        Person child = TestUtils.createDefaultPerson("11145678910");
        personDao.save(child);

        Person firstParent = TestUtils.createDefaultPerson("12345678910");
        personDao.save(firstParent);

        ApplicationCreationDto applicationCreationDto = TestUtils.createDefaultApplicationDto();
        applicationCreationDto.setChildId(child.getId());
        applicationCreationDto.setFirstParentId(firstParent.getId());

        Map<Integer, Long> priorityKindergarten = new HashMap<>();
        priorityKindergarten.put(1, kindergarten.getId());

        applicationCreationDto.setPriorityForKindergartenID(priorityKindergarten);

        applicationController.addApplication(applicationCreationDto);

        //ANTRAS PRASYMAS

        Person child2 = TestUtils.createDefaultPerson("22245678910");
        personDao.save(child2);

        Person firstParent2 = TestUtils.createDefaultPerson("22225678910");
        personDao.save(firstParent2);

        ApplicationCreationDto applicationCreationDto2 = TestUtils.createDefaultApplicationDto();
        applicationCreationDto2.setChildId(child2.getId());
        applicationCreationDto2.setFirstParentId(firstParent2.getId());

        Map<Integer, Long> priorityKindergarten2 = new HashMap<>();
        priorityKindergarten2.put(1, kindergarten.getId());

        applicationCreationDto2.setPriorityForKindergartenID(priorityKindergarten2);

        applicationController.addApplication(applicationCreationDto2);


        assertEquals(2, applicationDao.findAll().size(), "Should get all applications");

    }






}

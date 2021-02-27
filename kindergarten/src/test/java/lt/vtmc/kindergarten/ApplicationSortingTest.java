package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.config.DataSeeder;
import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running algorithm")
public class ApplicationSortingTest {

    @Autowired
    private KindergartenService kindergartenService;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private QueueService queueService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private DataSeeder dataSeeder;

    @Autowired
    private AgeRangeDao ageRangeDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Test
    @DisplayName("when running count Child Age test")
    public void countChildAgeInYearsTest() {

        assertEquals(5, PersonService.countChildAge("61602183111"), "Should return correct child age in years");
        assertEquals(4, PersonService.countChildAge("61702183111"), "Should return correct child age in years");
        assertEquals(3, PersonService.countChildAge("61802183111"), "Should return correct child age in years");
        assertEquals(2, PersonService.countChildAge("61902183111"), "Should return correct child age in years");
        assertEquals(1, PersonService.countChildAge("62002183111"), "Should return correct child age in years");
        assertEquals(0, PersonService.countChildAge("62102183111"), "Should return correct child age in years");
    }


    @Test
    @DisplayName("when running application sorting test")
    @Transactional
    public void testSortingOfApplications() {
        //TODO should be shortened in the future
        Person parent = TestUtils.createDefaultPerson("12346578910");

        Person child1 = TestUtils.createDefaultPerson("61602300444");//5m, score:11
        child1.setFirstName("Bronė");
        child1.setLastName("Bujotaitė");

        Person child2 = TestUtils.createDefaultPerson("61502300333");//6m, score:11
        child2.setFirstName("Audrė");
        child2.setLastName("Rudgalvytė");

        Person child3 = TestUtils.createDefaultPerson("61702300222");//4m, score:11 + abc(Žvirblytė)
        child3.setLastName("Žvirblytė");

        Person child4 = TestUtils.createDefaultPerson("61702300111");//4m, score:11 + abc(Astrauskaitė)
        child4.setLastName("Astrauskaitė");

        Person child5 = TestUtils.createDefaultPerson("61802305555");//3m, score:14
        child5.setLastName("Daugelytė");


        personDao.save(parent);
        personDao.save(child1);
        personDao.save(child2);
        personDao.save(child3);
        personDao.save(child4);
        personDao.save(child5);

        Person parentPerson = personDao.findByPersonalCode(parent.getPersonalCode());
        Person child1Id = personDao.findByPersonalCode(child1.getPersonalCode());
        Person child2Id = personDao.findByPersonalCode(child2.getPersonalCode());
        Person child3Id = personDao.findByPersonalCode(child3.getPersonalCode());
        Person child4Id = personDao.findByPersonalCode(child4.getPersonalCode());
        Person child5Id = personDao.findByPersonalCode(child5.getPersonalCode());

        QueueDtoWithOpeningDate queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);


        districtService.addDistrict(new DistrictDto(TestUtils.createDefaultDistrict("Antakalnis")));

        Kindergarten kindergarten1 = TestUtils.createDefaultKindergarten("12355456");
        kindergarten1.setDistrict(districtService.findDistrict("Antakalnis"));
        kindergartenService.addKindergarten(new KindergartenDto(kindergarten1));
        Long kindergarten1Id = kindergartenService.getKindergartens().get(0).getId();

        //1 application
        ApplicationCreationDto application1 = TestUtils.createDefaultApplicationDto();
        application1.setFirstParentId(parentPerson.getId());
        application1.setChildId(child1Id.getId());
        application1.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergarten1Id);
        }});

        //2 application
        ApplicationCreationDto application2 = TestUtils.createDefaultApplicationDto();
        application2.setFirstParentId(parentPerson.getId());
        application2.setChildId(child2Id.getId());
        application2.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergarten1Id);
        }});

        //3 application
        ApplicationCreationDto application3 = TestUtils.createDefaultApplicationDto();
        application3.setFirstParentId(parentPerson.getId());
        application3.setChildId(child3Id.getId());
        application3.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergarten1Id);
        }});

        //4 application
        ApplicationCreationDto application4 = TestUtils.createDefaultApplicationDto();
        application4.setFirstParentId(parentPerson.getId());
        application4.setChildId(child4Id.getId());
        application4.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergarten1Id);
        }});

        //5 application
        ApplicationCreationDto application5 = new ApplicationCreationDto();
        application5.setDate(new Date());
        application5.setIsAdopted(true);
        application5.setIsGuardianDisabled(true);
        application5.setIsMultiChild(true);
        application5.setIsGuardianStudent(true);
        application5.setFirstParentId(parentPerson.getId());
        application5.setChildId(child5Id.getId());
        application5.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergarten1Id);
        }});

        applicationService.addApplication(application1);
        applicationService.addApplication(application2);
        applicationService.addApplication(application3);
        applicationService.addApplication(application4);
        applicationService.addApplication(application5);

        List<Application> applicationList = applicationDao.findAll();

        applicationList.stream().forEach(application ->
                {
                    application.setApplicationStatus(ApplicationStatusEnum.WAITING);
                    applicationDao.save(application);
                }
        );

        List<Application> sortedApplications = applicationService.getSortedApplications();

        assertTrue(sortedApplications.get(0).getChild().getPersonalCode() == "61802305555");
        assertTrue(sortedApplications.get(1).getChild().getPersonalCode() == "61502300333");
        assertTrue(sortedApplications.get(2).getChild().getPersonalCode() == "61602300444");
        assertTrue(sortedApplications.get(3).getChild().getPersonalCode() == "61702300111");
        assertTrue(sortedApplications.get(4).getChild().getPersonalCode() == "61702300222");
    }


    @Test
    @Disabled
    @DisplayName("when running application sorting test")
    @Transactional
    public void testApplicationSubmission() {
        //TODO should be shortened in the future
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(7);
        ageRangeDao.save(ageRange);

        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtService.addDistrict(new DistrictDto(district));

        Person parent = TestUtils.createDefaultPerson("12346578910");
        Person child1 = TestUtils.createDefaultPerson("61602300188");
        Person child2 = TestUtils.createDefaultPerson("61002300178");
        Person child3 = TestUtils.createDefaultPerson("61702300188");
        child3.setLastName("Zukazandyte");
        Person child4 = TestUtils.createDefaultPerson("61702300198");
        child4.setLastName("Abudabudaite");

        personDao.save(parent);
        personDao.save(child1);
        personDao.save(child2);
        personDao.save(child3);
        personDao.save(child4);

        Person parentPerson = personDao.findByPersonalCode(parent.getPersonalCode());
        Person child1Id = personDao.findByPersonalCode(child1.getPersonalCode());
        Person child2Id = personDao.findByPersonalCode(child2.getPersonalCode());
        Person child3Id = personDao.findByPersonalCode(child3.getPersonalCode());
        Person child4Id = personDao.findByPersonalCode(child4.getPersonalCode());

        QueueDtoWithOpeningDate queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);

        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("12355456");
        kindergarten.setDistrict(districtService.findDistrict("Antakalnis"));

//        Group group1 = TestUtils.createDefaultGroup(kindergarten);
//        groupDao.save(group1);
//        kindergarten.addGroup(group1);
        kindergartenService.addKindergarten(new KindergartenDto(kindergarten));
        //FIXME replace with TestUtils group creation
        dataSeeder.createGroupForKindergarten("12355456", 1, 7, 3);


        Kindergarten kindergarten2 = TestUtils.createDefaultKindergarten("33355456");
        kindergarten2.setDistrict(districtService.findDistrict("Antakalnis"));
//        Group group2 = TestUtils.createDefaultGroup(kindergarten2);
//        groupDao.save(group2);
//        kindergarten2.addGroup(group2);
        kindergartenService.addKindergarten(new KindergartenDto(kindergarten2));
        //FIXME replace with TestUtils group creation
        dataSeeder.createGroupForKindergarten("33355456", 1, 7, 3);

        Long kindergartenId = kindergartenService.getKindergartens().get(0).getId();
        Long kindergarten2Id = kindergartenService.getKindergartens().get(1).getId();

        ApplicationCreationDto application1 = TestUtils.createDefaultApplicationDto();
        application1.setFirstParentId(parentPerson.getId());
        application1.setChildId(child1Id.getId());

        application1.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergartenId);
            put(2, kindergarten2Id);
        }});


        ApplicationCreationDto application2 = TestUtils.createDefaultApplicationDto();
        application2.setFirstParentId(parentPerson.getId());
        application2.setChildId(child2Id.getId());
        application2.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergartenId);
        }});

        ApplicationCreationDto application3 = TestUtils.createDefaultApplicationDto();
        application3.setFirstParentId(parentPerson.getId());
        application3.setChildId(child3Id.getId());
        application3.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergartenId);
        }});

        ApplicationCreationDto application4 = TestUtils.createDefaultApplicationDto();
        application4.setFirstParentId(parentPerson.getId());
        application4.setChildId(child4Id.getId());
        application4.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergartenId);
        }});


        applicationService.addApplication(application1);
        applicationService.addApplication(application2);
        applicationService.addApplication(application3);
        applicationService.addApplication(application4);


        List<Application> sortedApplications = applicationService.getSortedApplications();

        assertTrue(sortedApplications.get(0).getChild().getPersonalCode() == "61002300178");
        assertTrue(sortedApplications.get(1).getChild().getPersonalCode() == "61602300188");
        assertTrue(sortedApplications.get(2).getChild().getPersonalCode() == "61702300198");
        assertTrue(sortedApplications.get(3).getChild().getPersonalCode() == "61702300188");


    }
}

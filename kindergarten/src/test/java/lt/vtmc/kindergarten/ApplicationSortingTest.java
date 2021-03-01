package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.dao.*;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.*;
import lt.vtmc.kindergarten.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@SpringBootTest
@DisplayName("When running application sorting")
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
    @DisplayName("when running application sorting by score, then by age, then by child last name")
    @Transactional
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testSortingOfApplications() {
        //TODO should be shortened in the future
        Person parent = TestUtils.createDefaultPerson("12346578910", "Antanas", "Antanaitis");
        Person child1 = TestUtils.createDefaultPerson("61602300444", "Bronė", "Bujotaitė");//5m, score:11
        Person child2 = TestUtils.createDefaultPerson("61502300333", "Audrė", "Rudgalvytė");//6m, score:11
        Person child3 = TestUtils.createDefaultPerson("61702300222", "Ona", "Žvirblytė");//4m, score:11 + abc
        Person child4 = TestUtils.createDefaultPerson("61702300111", "Onytė", "Astrauskaitė");//4m, score:11 + abc
        Person child5 = TestUtils.createDefaultPerson("61802305555", "Ieva", "Daugelytė");//3m, score:14

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

        createKindergartens();
        Long kindergartenId = kindergartenService.getKindergartens().get(0).getId();
        Long kindergarten2Id = kindergartenService.getKindergartens().get(1).getId();

        //Applications:
        ApplicationCreationDto application1 = createApplication(parentPerson.getId(), child1Id.getId(), kindergartenId, kindergarten2Id, false, false, false, false);
        ApplicationCreationDto application2 = createApplication(parentPerson.getId(), child2Id.getId(), kindergartenId, kindergarten2Id, false, false, false, false);
        ApplicationCreationDto application3 = createApplication(parentPerson.getId(), child3Id.getId(), kindergartenId, kindergarten2Id, false, false, false, false);
        ApplicationCreationDto application4 = createApplication(parentPerson.getId(), child4Id.getId(), kindergartenId, kindergarten2Id, false, false, false, false);
        ApplicationCreationDto application5 = createApplication(parentPerson.getId(), child5Id.getId(), kindergartenId, kindergarten2Id, true, true, true, true);

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
    @DisplayName("when running application submission")
    @Transactional
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testApplicationSubmission() {
        //TODO should be shortened in the future
        QueueDtoWithOpeningDate queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);

        districtService.addDistrict(new DistrictDto(TestUtils.createDefaultDistrict("Antakalnis")));

        createKindergartens();
        Long kindergartenId = kindergartenService.getKindergartens().get(0).getId();
        Long kindergarten2Id = kindergartenService.getKindergartens().get(1).getId();

        Person parent = TestUtils.createDefaultPerson("48501158913", "Antanas", "Antanaitis");
        Person child1 = TestUtils.createDefaultPerson("61902124144", "Ieva", "Zbignaitė");
        Person child2 = TestUtils.createDefaultPerson("61902123133", "Miglė", "Adomaitytė");
        Person child3 = TestUtils.createDefaultPerson("61902122122", "Ona", "Kurmytė");
        Person child4 = TestUtils.createDefaultPerson("61902121011", "Rugilė", "Liepatė");
        Person child5 = TestUtils.createDefaultPerson("61802121000", "Agota", "Morkevičiūtė");

        Person child6 = new Person();
        child6.setFirstName("Ieva");
        child6.setLastName("NEvilnietytė");
        child6.setPersonalCode("61802306666");
        child6.setAddress("Kanklių g.4");
        child6.setCity(CityEnum.OTHER);
        child6.setPostalCode("12355");
        child6.setTribeId("qwerty");

        personDao.save(parent);
        personDao.save(child1);
        personDao.save(child2);
        personDao.save(child3);
        personDao.save(child4);
        personDao.save(child5);
        personDao.save(child6);

        Person parentPerson = personDao.findByPersonalCode(parent.getPersonalCode());
        Person child1Id = personDao.findByPersonalCode(child1.getPersonalCode());
        Person child2Id = personDao.findByPersonalCode(child2.getPersonalCode());
        Person child3Id = personDao.findByPersonalCode(child3.getPersonalCode());
        Person child4Id = personDao.findByPersonalCode(child4.getPersonalCode());
        Person child5Id = personDao.findByPersonalCode(child5.getPersonalCode());
        Person child6Id = personDao.findByPersonalCode(child6.getPersonalCode());

        //Applications:
        ApplicationCreationDto application1 = createApplication(parentPerson.getId(), child1Id.getId(), kindergartenId, kindergarten2Id, true, true, false, false);
        ApplicationCreationDto application2 = createApplication(parentPerson.getId(), child2Id.getId(), kindergartenId, kindergarten2Id, true, true, false, false);
        ApplicationCreationDto application3 = createApplication(parentPerson.getId(), child3Id.getId(), kindergartenId, kindergarten2Id, true, true, true, false);
        ApplicationCreationDto application4 = createApplication(parentPerson.getId(), child4Id.getId(), kindergartenId, kindergarten2Id, true, true, true, true);
        ApplicationCreationDto application5 = createApplication(parentPerson.getId(), child5Id.getId(), kindergartenId, kindergarten2Id, true, true, true, true);
        ApplicationCreationDto application6 = createApplication(parentPerson.getId(), child6Id.getId(), kindergartenId, kindergarten2Id, true, true, true, true);

        applicationService.addApplication(application1);
        applicationService.addApplication(application2);
        applicationService.addApplication(application3);
        applicationService.addApplication(application4);
        applicationService.addApplication(application5);
        applicationService.addApplication(application6);

        List<Application> applicationList = applicationDao.findAll();
        applicationList.stream().forEach(application ->
                {
                    application.setApplicationStatus(ApplicationStatusEnum.WAITING);
                    applicationDao.save(application);
                }
        );

        applicationService.calculateApplicationStatus();
        List<ApplicationAfterDistributionDto> appplicationsAfterDistribution = applicationService.getApplicationsAfterDistribution();

        assertEquals(14, appplicationsAfterDistribution.get(0).getScore());
        assertEquals(14, appplicationsAfterDistribution.get(1).getScore());
        assertEquals(13, appplicationsAfterDistribution.get(2).getScore());

        assertEquals(12, appplicationsAfterDistribution.get(3).getScore());
        assertEquals(12, appplicationsAfterDistribution.get(4).getScore());
        assertEquals(4, appplicationsAfterDistribution.get(5).getScore());

        assertEquals("APPROVED", appplicationsAfterDistribution.get(0).getStatus());
        assertEquals("APPROVED", appplicationsAfterDistribution.get(1).getStatus());
        assertEquals("APPROVED", appplicationsAfterDistribution.get(2).getStatus());
        assertEquals("APPROVED", appplicationsAfterDistribution.get(3).getStatus());
        assertEquals("UNCONFIRMED", appplicationsAfterDistribution.get(4).getStatus());
        assertEquals("UNCONFIRMED", appplicationsAfterDistribution.get(5).getStatus());

        assertEquals("Morkevičiūtė", appplicationsAfterDistribution.get(0).getChildLastName());
        assertEquals("Liepatė", appplicationsAfterDistribution.get(1).getChildLastName());
        assertEquals("Kurmytė", appplicationsAfterDistribution.get(2).getChildLastName());
        assertEquals("Adomaitytė", appplicationsAfterDistribution.get(3).getChildLastName());
        assertEquals("Zbignaitė", appplicationsAfterDistribution.get(4).getChildLastName());
        assertEquals("NEvilnietytė", appplicationsAfterDistribution.get(5).getChildLastName());
    }

    private void createKindergartens() {
        //Kindergarten 1
        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("32155451");
        kindergarten.setDistrict(districtService.findDistrict("Antakalnis"));

        AgeRange ageRange = createAgeRange(1, 2);
        AgeRange ageRange3 = createAgeRange(3, 4);
        Group group = createGroup(ageRange, kindergarten, 1);
        Group group3 = createGroup(ageRange3, kindergarten, 1);

        kindergarten.addGroup(group);
        kindergarten.addGroup(group3);
        kindergartenDao.save(kindergarten);

        //Kindergarten 2
        Kindergarten kindergarten2 = TestUtils.createDefaultKindergarten("15855458");
        kindergarten2.setDistrict(districtService.findDistrict("Antakalnis"));

        AgeRange ageRange2 = createAgeRange(2, 3);
        Group group2 = createGroup(ageRange2, kindergarten2, 2);

        kindergarten2.addGroup(group2);
        kindergartenDao.save(kindergarten2);
    }

    private ApplicationCreationDto createApplication(Long parentId, Long childId, Long kindergartenId, Long kindergarten2Id,
                                                     Boolean isAdopted, Boolean isGuardianDisables, Boolean isMultiChild, Boolean isGuardianStudent) {
        ApplicationCreationDto application = new ApplicationCreationDto();
        application.setDate(new Date());
        application.setIsAdopted(isAdopted);
        application.setIsGuardianDisabled(isGuardianDisables);
        application.setIsMultiChild(isMultiChild);
        application.setIsGuardianStudent(isGuardianStudent);
        application.setFirstParentId(parentId);
        application.setChildId(childId);
        application.setPriorityForKindergartenID(new HashMap<>() {{
            put(1, kindergartenId);
            put(2, kindergarten2Id);
        }});

        return application;
    }

    private AgeRange createAgeRange(int minAge, int maxAge) {
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(minAge);
        ageRange.setAgeMax(maxAge);
        ageRangeDao.save(ageRange);
        return ageRange;
    }

    private Group createGroup(AgeRange ageRange, Kindergarten kindergarten, int childrenCount) {
        Group group = new Group();
        group.setAgeRange(ageRange);
        group.setKindergartenId(kindergarten);
        group.setChildrenCount(childrenCount);
        return group;
    }


}

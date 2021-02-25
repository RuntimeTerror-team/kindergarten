package lt.vtmc.kindergarten;

import lt.vtmc.kindergarten.dao.PersonDao;
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
    private PersonDao personDao;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private QueueService queueService;

    @Autowired
    private DistrictService districtService;


    @Test
    @DisplayName("counting Child Age")
    public void countChildAgeInYearsTest() {

        assertEquals(5, countChildAge("61602183111"), "Should return child age in years");
        assertEquals(4, countChildAge("61702183111"), "Should return child age in years");
        assertEquals(3, countChildAge("61802183111"), "Should return child age in years");
        assertEquals(2, countChildAge("61902183111"), "Should return child age in years");
        assertEquals(1, countChildAge("62002183111"), "Should return child age in years");
        assertEquals(0, countChildAge("62102183111"), "Should return child age in years");

    }

    @Test
    @DisplayName("")
    @Transactional
    public void testSortingOfApplications(){
        District district =TestUtils.createDefaultDistrict("Antakalnis");
        districtService.addDistrict(new DistrictDto(district));

        Person parent = TestUtils.createDefaultPerson("12346578910");
        Person child1 = TestUtils.createDefaultPerson("61602300188");
        Person child2 = TestUtils.createDefaultPerson("61502300178");
        Person child3 = TestUtils.createDefaultPerson("61702300188");
        child3.setLastName("Zukazandyte");
        Person child4 = TestUtils.createDefaultPerson("61702300198");
        child4.setLastName("Abudabudaite");

        personDao.save(parent);
        personDao.save(child1);
        personDao.save(child2);
        personDao.save(child3);
        personDao.save(child4);

        Person parentPerson=personDao.findByPersonalCode(parent.getPersonalCode());
        Person child1Id=personDao.findByPersonalCode(child1.getPersonalCode());
        Person child2Id=personDao.findByPersonalCode(child2.getPersonalCode());
        Person child3Id=personDao.findByPersonalCode(child3.getPersonalCode());
        Person child4Id=personDao.findByPersonalCode(child4.getPersonalCode());

        QueueDtoWithOpeningDate queue = TestUtils.createDefaultQueue();
        queueService.addQueueWithOpeningDate(queue);

        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("12355456");
        kindergarten.setDistrict(districtService.findDistrict("Antakalnis"));
        kindergartenService.addKindergarten(new KindergartenDto(kindergarten));

        Long kindergartenId = kindergartenService.getKindergartens().get(0).getId();

        ApplicationCreationDto application1 = TestUtils.createDefaultApplicationDto();
        application1.setFirstParentId(parentPerson.getId());
        application1.setChildId(child1Id.getId());

        application1.setPriorityForKindergartenID(new HashMap<>(){{
            put(1,kindergartenId);
        }});


        ApplicationCreationDto application2 = TestUtils.createDefaultApplicationDto();
        application2.setFirstParentId(parentPerson.getId());
        application2.setChildId(child2Id.getId());
        application2.setPriorityForKindergartenID(new HashMap<>(){{
            put(1,kindergartenId);
        }});

        ApplicationCreationDto application3 = TestUtils.createDefaultApplicationDto();
        application3.setFirstParentId(parentPerson.getId());
        application3.setChildId(child3Id.getId());
        application3.setPriorityForKindergartenID(new HashMap<>(){{
            put(1,kindergartenId);
        }});

        ApplicationCreationDto application4 = TestUtils.createDefaultApplicationDto();
        application4.setFirstParentId(parentPerson.getId());
        application4.setChildId(child4Id.getId());
        application4.setPriorityForKindergartenID(new HashMap<>(){{
            put(1,kindergartenId);
        }});


        applicationService.addApplication(application1);
        applicationService.addApplication(application2);
        applicationService.addApplication(application3);
        applicationService.addApplication(application4);

        List<Application> sortedApplications = applicationService.getSortedApplications();

        assertTrue(sortedApplications.get(0).getChild().getPersonalCode() == "61502300178");
        assertTrue(sortedApplications.get(1).getChild().getPersonalCode() == "61602300188");
        assertTrue(sortedApplications.get(2).getChild().getPersonalCode() == "61702300198");
        assertTrue(sortedApplications.get(3).getChild().getPersonalCode() == "61702300188");
    }

    public int countChildAge(String personalCode) {

        int birthdayYear = Integer.parseInt(personalCode.substring(1, 3));

        LocalDate localDate = LocalDate.now();

        int currentYear = localDate.getYear() - 2000;

        return currentYear - birthdayYear;
    }






}

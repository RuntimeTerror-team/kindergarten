package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.TestUtils;
import lt.vtmc.kindergarten.dao.AgeRangeDao;
import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.dao.GroupDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;


import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@WithMockUser(username="administratorius",roles={"EDUCATION_SPECIALIST"})
@DisplayName("When running Kindergarten controller")
public class KindergartenControllerTest {

    @Autowired
    private KindergartenController kindergartenController;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    @Autowired
    private AgeRangeDao ageRangeDao;

    @Autowired
    private GroupDao groupDao;

    @Test
    @Order(1)
    @DisplayName("create kindergarten")
    void testCreatingKindergarten() {
        District district = TestUtils.createDefaultDistrict("Antakalnis");
        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("12345678");
        districtDao.save(district);
        kindergarten.setDistrict(district);
        kindergartenController.addKindergarten(new KindergartenDto(kindergarten));

        assertEquals(1, kindergartenController.getKindergartens().size(), "should create kindergarten");
    }

    @Test
    @Order(2)
    @DisplayName("get all groups by kindergarten id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetAllGroupsByKindergartenId() {
        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtDao.save(district);
        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("12345678");
        kindergarten.setDistrict(district);

        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);
        ageRangeDao.save(ageRange);

        Group group = new Group();
        group.setAgeRange(ageRange);
        group.setKindergartenId(kindergarten);
        group.setChildrenCount(10);
        kindergartenDao.save(kindergarten);
        groupDao.save(group);

        kindergarten.addGroup(group);

        kindergartenDao.save(kindergarten);

        assertEquals(1, kindergartenController.getGroups(kindergarten.getId()).size(), "Should get all groups by kindergarten id");
    }

    @Test
    @Order(3)
    @DisplayName("get one group by kindergarten id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetSingleGroupByKindergartenId() {
        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtDao.save(district);
        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("12345678");
        kindergarten.setDistrict(district);

        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);
        ageRangeDao.save(ageRange);

        Group group = new Group();
        group.setAgeRange(ageRange);
        group.setKindergartenId(kindergarten);
        group.setChildrenCount(10);
        kindergartenDao.save(kindergarten);
        groupDao.save(group);

        kindergarten.addGroup(group);
        kindergartenDao.save(kindergarten);

        Group group2 = new Group();
        group2.setAgeRange(ageRange);
        group2.setKindergartenId(kindergarten);
        group2.setChildrenCount(13);
        kindergartenDao.save(kindergarten);
        groupDao.save(group2);

        kindergarten.addGroup(group2);
        kindergartenDao.save(kindergarten);

        assertEquals(group2.getChildrenCount(), kindergartenController.getGroup(kindergarten.getId(), group2.getId()).getChildrenCount(), "Should get single group by kindergarten id");
    }

    @Test
    @Order(4)
    @DisplayName("get all kindergartens")
    void testGetAllKindergartens() {
        District district = TestUtils.createDefaultDistrict("Antakalnis");
        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("12345678");

        districtDao.save(district);

        KindergartenDto kindergartenDto = new KindergartenDto(kindergarten);
        kindergartenDto.setDistrict(district);
        kindergartenDto.setWebsite("www.darzelis.lt");
        kindergartenDto.setCity(CityEnum.VILNIUS);
        kindergartenDto.setEmail("darzelis@gmail.com");
        kindergartenDto.setAddress("darzelio g. 45");
        kindergartenDto.setTitle("Darzelis");

        kindergartenController.addKindergarten(kindergartenDto);

        District district2 = new District();
        districtDao.save(district2);

        KindergartenDto secondKindergarten = new KindergartenDto(TestUtils.createDefaultKindergarten("190055590"));
        secondKindergarten.setDistrict(district2);
        kindergartenController.addKindergarten(secondKindergarten);

        assertEquals(2, kindergartenController.getKindergartens().size(), "should get all kindergartens");
    }

    @Test
    @Order(5)
    @DisplayName("update kindergarten by id")
    void testUpdateKindergarten() {
        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtDao.save(district);

        KindergartenDto kindergartenDto = new KindergartenDto(TestUtils.createDefaultKindergarten("12345688"));
        kindergartenDto.setDistrict(district);

        kindergartenController.addKindergarten(kindergartenDto);

        KindergartenDto kindergartenDto2 = new KindergartenDto(TestUtils.createDefaultKindergarten("12345682"));

        kindergartenDto2.setTitle("Pušaitė Update");
        kindergartenDto2.setAddress("Bistryčios g. 333");
        kindergartenDto2.setPostalCode("10321");
        District district2 = TestUtils.createDefaultDistrict("Pasilaiciai");
        districtDao.save(district2);
        kindergartenDto2.setDistrict(district2);

        kindergartenController.updateKindergarten(2L, kindergartenDto2);

        assertEquals("Pušaitė Update", kindergartenController.getKindergarten(2L).getTitle(), "should update the title correctly");
        assertEquals("Bistryčios g. 333", kindergartenController.getKindergarten(2L).getAddress(), "should update the address correctly");
        assertEquals("10321", kindergartenController.getKindergarten(2L).getPostalCode(), "should update the postal code correctly");
    }

    @Test
    @Order(6)
    @DisplayName("disallow duplicate kindergarten company codes")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void testCreateKindergartenWithDuplicateCompanyCode() {

        Kindergarten kindergarten = TestUtils.createDefaultKindergarten("12345888");
        KindergartenDto kindergartenDto = new KindergartenDto(TestUtils.createDefaultKindergarten("12345888"));
        District district = TestUtils.createDefaultDistrict("Antakalnis");
        districtDao.save(district);
        kindergartenDto.setDistrict(district);
        kindergartenController.addKindergarten(kindergartenDto);

        kindergartenDto.setDistrict(district);
        KindergartenDto secondKindergarten = new KindergartenDto(TestUtils.createDefaultKindergarten(kindergarten.getCompanyCode()));

        District district2 = TestUtils.createDefaultDistrict("Pilaite");
        districtDao.save(district2);
        secondKindergarten.setDistrict(district2);

        ResponseEntity response = kindergartenController.addKindergarten(secondKindergarten);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}

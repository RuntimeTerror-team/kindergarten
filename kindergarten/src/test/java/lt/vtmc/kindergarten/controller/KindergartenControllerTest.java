package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;


import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running Kindergarten controller")
public class KindergartenControllerTest {

    @Autowired
    private KindergartenController kindergartenController;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private KindergartenDao kindergartenDao;

    private Kindergarten kindergarten;

    private District district;

    @BeforeEach
    private void init() {
        District district = KindergartenTestUtil.createDistrict();
        this.district = district;

        Kindergarten kindergarten = KindergartenTestUtil.createKindergarten();
        this.kindergarten = kindergarten;
    }

    @Test
    @Order(1)
    @DisplayName("create kindergarten")
    void testCreatingKindergarten(){
        districtDao.save(district);
        kindergarten.setDistrict(district);
        kindergartenController.addKindergarten(new KindergartenDto(kindergarten));

        assertEquals(1, kindergartenController.getKindergartens().size(), "should create kindergarten");
    }

    @Test
    @Order(2)
    @DisplayName("get all groups by kindergarten id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetAllGroupsByKindergartenId(){

        kindergarten.setDistrict(this.district);

        Group group = new Group();
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);
        group.setAgeRange(ageRange);
        group.setKindergartenId(kindergarten);
        group.setChildrenCount(10);
        group.setTitle("Pukelis");
        kindergarten.addGroup(group);

        districtDao.save(district);
        kindergartenDao.save(kindergarten);

        assertEquals(1, kindergartenController.getGroups(kindergarten.getId()).size(), "Should get all groups by kindergarten id");
    }

    @Test
    @Order(3)
    @DisplayName("get one group by kindergarten id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetSingleGroupByKindergartenId(){
        districtDao.save(district);
        kindergarten.setDistrict(district);

        Group group = new Group();
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);
        group.setAgeRange(ageRange);
        group.setKindergartenId(kindergarten);
        group.setChildrenCount(10);
        group.setTitle("Pukelis");

        Group group2 = new Group();
        AgeRange ageRange2 = new AgeRange();
        ageRange2.setAgeMin(3);
        ageRange2.setAgeMax(4);
        group2.setAgeRange(ageRange2);
        group2.setKindergartenId(kindergarten);
        group2.setChildrenCount(10);
        group2.setTitle("Zva");

        kindergarten.addGroup(group);
        kindergarten.addGroup(group2);

        kindergartenDao.save(kindergarten);

        assertEquals("Zva", kindergartenController.getGroup(kindergarten.getId(),group2.getId()).getTitle(), "Should get single group by kindergarten id");
    }

    @Test
    @Order(4)
    @DisplayName("get all kindergartens")
    void testGetAllKindergartens(){
        districtDao.save(district);

        KindergartenDto kindergartenDto = new KindergartenDto(kindergarten);
        kindergartenDto.setDistrict(district);
        kindergartenController.addKindergarten(kindergartenDto);

        District district2 = new District();
        districtDao.save(district2);

        KindergartenDto secondKindergarten = new KindergartenDto(KindergartenTestUtil.createKindergarten());
        secondKindergarten.setCompanyCode("190055590");
        secondKindergarten.setDistrict(district2);
        kindergartenController.addKindergarten(secondKindergarten);

        assertEquals(2, kindergartenController.getKindergartens().size(), "should get all kindergartens");
    }

    @Test
    @Order(5)
    @DisplayName("update kindergarten by id")
    void testUpdateKindergarten(){
        districtDao.save(district);

        KindergartenDto kindergartenDto = new KindergartenDto(KindergartenTestUtil.createKindergarten());
        kindergartenDto.setDistrict(district);

        kindergartenController.addKindergarten(kindergartenDto);

        KindergartenDto kindergartenDto2 = new KindergartenDto(KindergartenTestUtil.createKindergarten());

        kindergartenDto2.setTitle("Pušaitė Update");
        kindergartenDto2.setAddress("Bistryčios g. 333");
        kindergartenDto2.setPostalCode("10321");
        District district2 = KindergartenTestUtil.createDistrict();
        district2.setTitle("Pasilaiciai");
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
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreateKindergartenWithDuplicateCompanyCode(){
        districtDao.save(district);
        KindergartenDto kindergartenDto = new KindergartenDto(KindergartenTestUtil.createKindergarten());
        kindergartenDto.setDistrict(district);
        kindergartenController.addKindergarten(kindergartenDto);

        KindergartenDto secondKindergarten = new KindergartenDto(KindergartenTestUtil.createKindergarten());
        secondKindergarten.setCompanyCode(kindergarten.getCompanyCode());
        District district2 = KindergartenTestUtil.createDistrict();
        district2.setTitle("Karoliniskes");
        districtDao.save(district2);

        ResponseEntity response =  kindergartenController.addKindergarten(secondKindergarten);

        assertEquals( HttpStatus.CONFLICT, response.getStatusCode());
    }

}

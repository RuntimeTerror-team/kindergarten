package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.dao.KindergartenDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("When running Kindergarten controller")
public class KindergartenControllerTest {

    @Autowired
    private KindergartenController kindergartenController;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private KindergartenDao kindergartenDao;


    @Test
    @Order(1)
    @DisplayName("create kindergarten")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreatingKindergarten(){
        District district = new District();
        district.setTitle("Antakalnis");
        districtDao.save(district);

        KindergartenDto kindergartenDto = new KindergartenDto();
        kindergartenDto.setTitle("Pušaitė");
        kindergartenDto.setAddress("Bistryčios g. 3");
        kindergartenDto.setCity(CityEnum.VILNIUS);
        kindergartenDto.setPostalCode("10320");
        kindergartenDto.setPhoneNumber("852343900");
        kindergartenDto.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto.setWebsite("www.darzelispusaite.lt");
        kindergartenDto.setCompanyCode("190025890");
        kindergartenDto.setDistrict(district);

        kindergartenController.addKindergarten(kindergartenDto);

        assertEquals(1, kindergartenController.getKindergartens().size(), "should create kindergarten");
    }

    @Test
    @Order(2)
    @DisplayName("get all groups by kindergarten id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetAllGroupsByKindergartenId(){
        District district = new District();
        district.setTitle("Antakalnis");
        districtDao.save(district);

        Kindergarten kindergarten = new Kindergarten();
        kindergarten.setTitle("Pušaitė");
        kindergarten.setAddress("Bistryčios g. 3");
        kindergarten.setCity(CityEnum.VILNIUS);
        kindergarten.setPostalCode("10320");
        kindergarten.setPhoneNumber("852343900");
        kindergarten.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergarten.setWebsite("www.darzelispusaite.lt");
        kindergarten.setCompanyCode("190025890");
        kindergarten.setDistrict(district);

        Group group = new Group();
        AgeRange ageRange = new AgeRange();
        ageRange.setAgeMin(1);
        ageRange.setAgeMax(2);
        group.setAgeRange(ageRange);
        group.setKindergartenId(kindergarten);
        group.setChildrenCount(10);
        group.setTitle("Pukelis");
        kindergarten.addGroup(group);

        kindergartenDao.save(kindergarten);

        assertEquals(1, kindergartenController.getGroups(kindergarten.getId()).size(), "Should get all groups by kindergarten id");
    }

    @Test
    @Order(3)
    @DisplayName("get one group by kindergarten id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetSingleGroupByKindergartenId(){
        District district = new District();
        district.setTitle("Antakalnis");
        districtDao.save(district);

        Kindergarten kindergarten = new Kindergarten();
        kindergarten.setTitle("Pušaitė");
        kindergarten.setAddress("Bistryčios g. 3");
        kindergarten.setCity(CityEnum.VILNIUS);
        kindergarten.setPostalCode("10320");
        kindergarten.setPhoneNumber("852343900");
        kindergarten.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergarten.setWebsite("www.darzelispusaite.lt");
        kindergarten.setCompanyCode("190025890");
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
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testGetAllKindergartens(){
        District district = new District();
        district.setTitle("Antakalnis");
        districtDao.save(district);

        KindergartenDto kindergartenDto = new KindergartenDto();
        kindergartenDto.setTitle("Pušaitė");
        kindergartenDto.setAddress("Bistryčios g. 3");
        kindergartenDto.setCity(CityEnum.VILNIUS);
        kindergartenDto.setPostalCode("10320");
        kindergartenDto.setPhoneNumber("+37052343900");
        kindergartenDto.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto.setWebsite("www.darzelispusaite.lt");
        kindergartenDto.setCompanyCode("190025890");
        kindergartenDto.setDistrict(district);
        kindergartenController.addKindergarten(kindergartenDto);

        District district2 = new District();
        district2.setTitle("Žirmūnai");
        districtDao.save(district2);

        KindergartenDto kindergartenDto2 = new KindergartenDto();
        kindergartenDto2.setTitle("Smalsučiai");
        kindergartenDto2.setAddress(" Minties g. 40");
        kindergartenDto2.setCity(CityEnum.VILNIUS);
        kindergartenDto2.setPostalCode("09221");
        kindergartenDto2.setPhoneNumber("852343900");
        kindergartenDto2.setEmail("rastine@smalsuciai.vilnius.lm.lt");
        kindergartenDto2.setWebsite("www.vilniaussmalsuciai.lt");
        kindergartenDto2.setCompanyCode("190055590");
        kindergartenDto2.setDistrict(district2);
        kindergartenController.addKindergarten(kindergartenDto2);

        assertEquals(2, kindergartenController.getKindergartens().size(), "should get all kindergartens");
    }

    @Test
    @Order(5)
    @DisplayName("update kindergarten by id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testUpdateKindergarten(){
        District district = new District();
        district.setTitle("Antakalnis");
        districtDao.save(district);

        KindergartenDto kindergartenDto = new KindergartenDto();
        kindergartenDto.setTitle("Pušaitė");
        kindergartenDto.setAddress("Bistryčios g. 3");
        kindergartenDto.setCity(CityEnum.VILNIUS);
        kindergartenDto.setPostalCode("10320");
        kindergartenDto.setPhoneNumber("852343900");
        kindergartenDto.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto.setCompanyCode("190055590");
        kindergartenDto.setWebsite("www.darzelispusaite.lt");
        kindergartenDto.setDistrict(district);

        kindergartenController.addKindergarten(kindergartenDto);

        KindergartenDto kindergartenDto2 = new KindergartenDto();
        kindergartenDto2.setTitle("Pušaitė Update");
        kindergartenDto2.setAddress("Bistryčios g. 333");
        kindergartenDto2.setCity(CityEnum.VILNIUS);
        kindergartenDto2.setPostalCode("10321");
        kindergartenDto2.setPhoneNumber("852343900");
        kindergartenDto2.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto2.setCompanyCode("190055590");
        kindergartenDto2.setWebsite("www.darzelispusaite.lt");
        kindergartenDto2.setDistrict(district);

        kindergartenController.updateKindergarten(2L, kindergartenDto2);

        assertEquals("Pušaitė Update", kindergartenController.getKindergarten(2L).getTitle(), "should update the title correctly");
        assertEquals("Bistryčios g. 333", kindergartenController.getKindergarten(2L).getAddress(), "should update the address correctly");
        assertEquals("10321", kindergartenController.getKindergarten(2L).getPostalCode(), "should update the postal code correctly");
    }


}

package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.DistrictDao;
import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.dto.KindergartenDto;
import org.junit.jupiter.api.DisplayName;
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

    @Test
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
        kindergartenDto.setPhoneNumber(852343900L);
        kindergartenDto.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto.setWebsite("www.darzelispusaite.lt");
        kindergartenDto.setDistrict(district);

        kindergartenController.addKindergarten(kindergartenDto);

        assertEquals(1, kindergartenController.getKindergartens().size(), "should create kindergarten");
    }

    @Test
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
        kindergartenDto.setPhoneNumber(852343900L);
        kindergartenDto.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto.setWebsite("www.darzelispusaite.lt");
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
        kindergartenDto2.setPhoneNumber(852752119L);
        kindergartenDto2.setEmail("rastine@smalsuciai.vilnius.lm.lt");
        kindergartenDto2.setWebsite("www.vilniaussmalsuciai.lt");
        kindergartenDto2.setDistrict(district2);
        kindergartenController.addKindergarten(kindergartenDto2);

        assertEquals(2, kindergartenController.getKindergartens().size(), "should get all kindergartens");
    }

    @Test
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
        kindergartenDto.setPhoneNumber(852343900L);
        kindergartenDto.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto.setWebsite("www.darzelispusaite.lt");
        kindergartenDto.setDistrict(district);

        kindergartenController.addKindergarten(kindergartenDto);

        KindergartenDto kindergartenDto2 = new KindergartenDto();
        kindergartenDto2.setTitle("Pušaitė Update");
        kindergartenDto2.setAddress("Bistryčios g. 333");
        kindergartenDto2.setCity(CityEnum.VILNIUS);
        kindergartenDto2.setPostalCode("10321");
        kindergartenDto2.setPhoneNumber(852343900L);
        kindergartenDto2.setEmail("direktore@pusaite.vilnius.lm.lt");
        kindergartenDto2.setWebsite("www.darzelispusaite.lt");
        kindergartenDto2.setDistrict(district);

        kindergartenController.updateKindergarten(2L, kindergartenDto2);

        assertEquals("Pušaitė Update", kindergartenController.getKindergarten(2L).getTitle(), "should update the title correctly");
        assertEquals("Bistryčios g. 333", kindergartenController.getKindergarten(2L).getAddress(), "should update the address correctly");
        assertEquals("10321", kindergartenController.getKindergarten(2L).getPostalCode(), "should update the postal code correctly");
    }


}

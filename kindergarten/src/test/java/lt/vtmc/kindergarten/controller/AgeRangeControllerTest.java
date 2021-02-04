package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dto.AgeRangeDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running age Range Controller")
public class AgeRangeControllerTest {

    @Autowired
    private AgeRangeController ageRangeController;

    @Test
    @DisplayName("create age Range")
    void testCreatingAgeRange(){
        AgeRangeDto ageRangeDto = new AgeRangeDto();
        ageRangeDto.setId(1L);
        ageRangeDto.setMinAge(1);
        ageRangeDto.setMaxAge(2);

        ageRangeController.addAgeRange(ageRangeDto);
        ageRangeController.getAgeRanges();

        assertEquals(1, ageRangeController.getAgeRanges().size(), "should create district correctly");
    }

    @Test
    @DisplayName("get age Range list")
    void testGettingAllDistrictRangesWithSize3(){
        AgeRangeDto ageRangeDto1 = new AgeRangeDto();
        ageRangeDto1.setId(2L);
        ageRangeDto1.setMinAge(2);
        ageRangeDto1.setMaxAge(3);
        ageRangeController.addAgeRange(ageRangeDto1);

        AgeRangeDto ageRangeDto2 = new AgeRangeDto();
        ageRangeDto2.setId(3L);
        ageRangeDto2.setMinAge(3);
        ageRangeDto2.setMaxAge(4);
        ageRangeController.addAgeRange(ageRangeDto2);

        assertEquals(2, ageRangeController.getAgeRanges().size(), "should return age range list");
    }

    @Test
    @DisplayName("update age Range by id")
    void testUpdateAgeRange(){
        AgeRangeDto ageRangeDto = new AgeRangeDto();
        ageRangeDto.setId(1L);
        ageRangeDto.setMinAge(2);
        ageRangeDto.setMaxAge(3);
        ageRangeController.addAgeRange(ageRangeDto);

        AgeRangeDto ageRangeDto2 = new AgeRangeDto();
        ageRangeDto2.setMinAge(3);
        ageRangeDto2.setMaxAge(5);
        ageRangeController.updateAgeRange(1L, ageRangeDto2);

        assertEquals(3, ageRangeController.getAgeRange(1L).getMinAge(),"should update MinAge Range correctly");
        assertEquals(5, ageRangeController.getAgeRange(1L).getMaxAge(),"should update MaxAge Range correctly");

    }



}

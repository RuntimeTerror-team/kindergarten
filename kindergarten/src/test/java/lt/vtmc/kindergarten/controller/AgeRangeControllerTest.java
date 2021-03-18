package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dto.AgeRangeDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WithMockUser(username="ŠvietimoSpecialistas1",roles={"EDUCATION_SPECIALIST"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running age Range controller")
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

        ageRangeController.saveAgeRange(ageRangeDto);
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
        ageRangeController.saveAgeRange(ageRangeDto1);

        AgeRangeDto ageRangeDto2 = new AgeRangeDto();
        ageRangeDto2.setId(3L);
        ageRangeDto2.setMinAge(3);
        ageRangeDto2.setMaxAge(4);

        ageRangeController.saveAgeRange(ageRangeDto2);

        assertEquals(2, ageRangeController.getAgeRanges().size(), "should return age range list");
    }

}

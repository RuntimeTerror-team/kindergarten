package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dto.AgeRangeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("When running age Range Controller")
public class AgeRangeControllerTest {

    @Autowired
    private AgeRangeController ageRangeController;

    @Test
    @Order(1)
    @DisplayName("create ageRange")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreatingAgeRange(){
        AgeRangeDto ageRangeDto = new AgeRangeDto();
        ageRangeDto.setId(1L);
        ageRangeDto.setMinAge(1);
        ageRangeDto.setMaxAge(2);

        ageRangeController.addAgeRange(ageRangeDto);
        ageRangeController.getAgeRanges();

        assertEquals(1, ageRangeController.getAgeRanges().size(), "should create district correctly");
    }



}

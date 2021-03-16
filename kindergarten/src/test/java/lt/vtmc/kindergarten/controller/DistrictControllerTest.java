package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.TestUtils;
import lt.vtmc.kindergarten.domain.District;
import lt.vtmc.kindergarten.dto.DistrictDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;


import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@WithMockUser(username="administratorius",roles={"ADMIN", "EDUCATION_SPECIALIST"})
@DisplayName("When running District controller")
public class DistrictControllerTest {

    @Autowired
    private DistrictController districtController;

    @Test
    @Order(1)
    @DisplayName("create District")
    void testCreatingDistrict(){
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(1L);
        districtDto.setTitle("Antakalnis");

        districtController.addDistrict(districtDto);

        assertEquals(1, districtController.getDistricts().size(), "should create district correctly");
    }


    @Test
    @Order(2)
    @DisplayName("get district list")
    void testGettingAllDistrictsWithSize3(){
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(2L);
        districtDto.setTitle("Senamiestis");
        DistrictDto districtDto2 = new DistrictDto();
        districtDto2.setId(3L);
        districtDto2.setTitle("Naujamiestis");

        districtController.addDistrict(districtDto);
        districtController.addDistrict(districtDto2);
        districtController.getDistricts();

        assertEquals(2, districtController.getDistricts().size(), "should get the right size list");
    }


    @Test
    @Order(3)
    @DisplayName("update district by id")
    void testUpdateDistrict(){
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(1L);
        districtDto.setTitle("Valakampiai");

        districtController.addDistrict(districtDto);

        DistrictDto districtDto2 = new DistrictDto();
        districtDto2.setTitle("ValakampiaiUpdate");

        districtController.updateDistrict(1L, districtDto2);

        assertEquals("ValakampiaiUpdate", districtController.getDistrict(1L).getTitle(), "should update district by id correctly");
    }


    @Test
    @Order(4)
    @DisplayName("district title validation")
    void testDistrictTitleValidation(){
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            DistrictDto districtDto = new DistrictDto();
            districtDto.setId(1L);
            districtDto.setTitle("Ant");
            districtController.addDistrict(districtDto);
        });

        String errorMessage = exception.getMessage();

        assertTrue(errorMessage.contains("length must be between 5 and 20"),"Should validate if title respects validation");
    }



    @Test
    @Order(5)
    @DisplayName("district sorting test")
    void testDistrictSorting() {
        District district1 = TestUtils.createDefaultDistrict("Alpha");
        District district2 = TestUtils.createDefaultDistrict("Bravo");
        District district3 = TestUtils.createDefaultDistrict("Charlie");

        districtController.addDistrict(new DistrictDto(district3));
        districtController.addDistrict(new DistrictDto(district1));
        districtController.addDistrict(new DistrictDto(district2));

        List<DistrictDto> districts =  districtController.getDistricts();

        assertEquals(districts.get(0).getTitle(), district1.getTitle(), "Districts should be ordered alphabetically by title");
        assertEquals(districts.get(1).getTitle(), district2.getTitle(), "Districts should be ordered alphabetically by title");
        assertEquals(districts.get(2).getTitle(), district3.getTitle(), "Districts should be ordered alphabetically by title");
    }
}

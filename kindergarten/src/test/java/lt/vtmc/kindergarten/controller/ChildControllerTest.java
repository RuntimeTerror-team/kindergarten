package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.dto.ChildDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running Child controller")
public class ChildControllerTest {

    @Autowired
    ChildController childController;

    @Test
    @Order(1)
    @DisplayName("create Child")
    void testCreatingChild(){
        ChildDto childDto = new ChildDto();

        childDto.setFirstName("Antanas");
        childDto.setLastName("Antanaitis");
        childDto.setPersonalCode("49004180333");
        childDto.setStreetAddress("Kankliu g.4");
        childDto.setCity(CityEnum.VILNIUS);

        childController.addChild(childDto);

        assertEquals(1, childController.getChildren().size(), "should create child correctly");
    }
}

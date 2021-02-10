package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.domain.CityEnum;
import lt.vtmc.kindergarten.dto.ChildDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Disabled
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@DisplayName("When running Child controller")
public class ChildControllerTest {

    @Autowired
    ChildController childController;

    @Test
    @DisplayName("create Child")
    void testCreatingChild(){

        ChildDto childDto = new ChildDto();

        childDto.setFirstName("Antanas");
        childDto.setLastName("Antanaitis");
        childDto.setPersonalCode("39004180333");
        childDto.setStreetAddress("Upės g. 23");
        childDto.setCity(CityEnum.VILNIUS);

        childController.addChild(childDto);

        assertEquals(1, childController.getChildren().size(), "should create child");
        assertEquals("Antanas", childController.getChild(1L).getFirstName(), "should create child with correct first name");
        assertEquals("Antanaitis", childController.getChild(1L).getLastName(), "should create child with correct last name");
        assertEquals("39004180333", childController.getChild(1L).getPersonalCode(), "should create child with correct personal code");
        assertEquals("Upės g. 23", childController.getChild(1L).getStreetAddress(), "should create child with correct street address");
    }
}

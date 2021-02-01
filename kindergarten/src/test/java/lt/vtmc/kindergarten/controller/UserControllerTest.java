package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dto.UserInfo;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    @Order(1)
    void testGettingUserWithAdminRole() {
        String role = userController.getUser("administratorius").getRole();
        assertEquals("ADMIN", role);
    }

    @Test
    @Order(2)
    void testGettingListOfAllUsersWithSize1() {
        assertEquals(1, userController.getUsers().size());
    }

    @Test
    @Order(3)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreatingUserAndGettingIt() {
        UserInfo user = new UserInfo("ArnasJocys1", "Arnas", "Jocys", 123456789l, "ArnasJocys1", "GUARDIAN");

        userController.createUser(user);

        assertNotEquals(null, userController.getUser("ArnasJocys1"));
    }
}

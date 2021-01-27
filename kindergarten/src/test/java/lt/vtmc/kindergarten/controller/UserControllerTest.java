package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dto.UserInfo;
import org.junit.jupiter.api.BeforeAll;
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
    void testGettingUserWithAdminRole() {
        String role = userController.getUser("administratorius").getRole();
        assertEquals("ADMIN", role);
    }

    @Test
    @DirtiesContext
    void testGettingListOfAllUsersWithSize2() {
        UserInfo user = new UserInfo("ArnasJocys1", "Arnas", "Jocys", 123456789l, "ArnasJocys1", "ADMIN");
        assertEquals(2, userController.getUsers().size());
    }

    @Test
    @DirtiesContext
    void testCreatingUserAndGettingIt() {
        UserInfo user = new UserInfo("ArnasJocys1", "Arnas", "Jocys", 123456789l, "ArnasJocys1", "ADMIN");

        userController.createUser(user);

        assertNotEquals(null, userController.getUser("ArnasJocys1"));
    }
}

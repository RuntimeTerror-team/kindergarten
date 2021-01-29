package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    void testCreatingUserWithAdminRole() {
        String role = userController.getUser("administratorius").getRole();
        assertEquals("ADMIN", role);
    }

    @Test
    void testGettingListOfAllUsersWithSize1() {
        // because we have default admin user
        assertEquals(1, userController.getUsers().size());
    }

    @Test
    void testCreatingUserAndGettingListOfAllUsersWithSize2() {
        UserInfo user = new UserInfo("ArnasJocys1", "Arnas", "Jocys", 123456789l, "ArnasJocys1", "ADMIN");

        userController.createUser(user);

        assertEquals(2, userController.getUsers().size());
    }
}

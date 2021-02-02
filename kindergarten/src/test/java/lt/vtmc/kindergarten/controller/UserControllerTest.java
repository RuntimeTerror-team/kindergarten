package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


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
        UserDto user = new UserDto("ArnasJocys1", "Arnas", "Jocys", 123456789l, "ArnasJocys1", "GUARDIAN");

        userController.createUser(user);

        assertNotEquals(null, userController.getUser("ArnasJocys1"));
    }

    @Test
    @Order(4)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreatingEduSpecialistAndGettingIt() {
        UserDto user = new UserDto("ArnasJocys2", "Arnas", "Jocys", 123456789l, "ArnasJocys2", "EDUCATION_SPECIALIST");

        userController.createUser(user);

        assertEquals("EDUCATION_SPECIALIST", userController.getUser("ArnasJocys2").getRole());
    }

    @Test
    @Order(5)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreating2AdditionalUsersAndGettingListOfAllUsersWithSize3() {
        UserDto user1 = new UserDto("ArnasJocys1", "Arnas", "Jocys", 123456789l, "ArnasJocys1", "GUARDIAN");
        userController.createUser(user1);
        UserDto user2 = new UserDto("ArnasJocys2", "Arnas", "Jocys", 123456789l, "ArnasJocys2", "EDUCATION_SPECIALIST");
        userController.createUser(user2);

        assertEquals(3, userController.getUsers().size());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCreating2GuardiansWithSameNamesButCreatesDifferentUsernames() {
        UserDtoFromAdmin user = new UserDtoFromAdmin("Arnas", "Jocys","GUARDIAN");
        String createdUsername = userController.createUserFromAdmin(user);
        UserDtoFromAdmin user2 = new UserDtoFromAdmin("Arnas", "Jocys","GUARDIAN");
        String createdUsername2 = userController.createUserFromAdmin(user2);

        assertNotEquals(createdUsername, createdUsername2, "Creating 2 guardians with same names gives equal usernames");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCreatingEduSpecialist() {
        UserDtoFromAdmin user = new UserDtoFromAdmin("Arnas", "Jocys","EDUCATION_SPECIALIST");
        String createdUsername = userController.createUserFromAdmin(user);

        assertEquals("ŠvietimoSpecialistas1", createdUsername, "Education specialist is not created with username ŠvietimoSpecialistas1");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCreatingGuardianUsernameIsLongerOrEqualThan8() {
        UserDtoFromAdmin user = new UserDtoFromAdmin("Li", "An","GUARDIAN");
        String createdUsername = userController.createUserFromAdmin(user);

        assertTrue(createdUsername.length()>=8, "Username is not equal or longer than 8 characters");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCreatingGuardianUsernameIsShorterOrEqualThan30() {
        UserDtoFromAdmin user = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        String createdUsername = userController.createUserFromAdmin(user);

        assertTrue(createdUsername.length()<=30, "Username is not equal or shorter than 30 characters");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCreatedUsernameHasAtLeast1LowerCase1CapitalLetterAnd1Digit() {
        UserDtoFromAdmin user = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        String createdUsername = userController.createUserFromAdmin(user);

        Boolean lower = false;
        Boolean capital = false;
        Boolean digit = false;

        char[] letters = createdUsername.toCharArray();
        for (char letter : letters) {
            int intValue = letter;
            if (intValue >= 97 && intValue <=122) {
                lower = true;
            }
            if (intValue >= 65 && intValue <=90) {
                capital = true;
            }
            if (intValue >= 48 && intValue <=57) {
                digit = true;
            }
        }

        assertTrue(lower && capital && digit, "Username does not contain at least 1 lowercase letter, 1 capital letter and 1 digit");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCreatingGuardianWithLowerCaseNamesGivesPascalCaseUsernameAndNames() {
        UserDtoFromAdmin user = new UserDtoFromAdmin("arnas", "jocys","GUARDIAN");
        String createdUsername = userController.createUserFromAdmin(user);

        assertEquals("ArnasJocys1", userController.getUser(createdUsername).getUsername(), "Pascal case Username is not created");
        assertEquals("Arnas", userController.getUser(createdUsername).getFirstName(), "Pascal case firstname is not created");
        assertEquals("Jocys", userController.getUser(createdUsername).getLastName(), "Pascal case lastname is not created");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCannotCreateSecondEducationSpecialist() {
        UserDtoFromAdmin user = new UserDtoFromAdmin("Arnas", "Jocys","EDUCATION_SPECIALIST");
        userController.createUserFromAdmin(user);
        UserDtoFromAdmin user2 = new UserDtoFromAdmin("Li", "Yu","EDUCATION_SPECIALIST");
        String createdUsername2 = userController.createUserFromAdmin(user2);

        assertEquals("Švietimo specialistas jau egzistuoja. Prisijungimo vardas: ŠvietimoSpecialistas1", createdUsername2, "Trying to create second education specialist does not give info message that education specialist is already created");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testAdminCreating10LongGuardianUsernameLastOneIsShorterOrEqualThan30() {
        UserDtoFromAdmin user1 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user1);
        UserDtoFromAdmin user2 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user2);
        UserDtoFromAdmin user3 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user3);
        UserDtoFromAdmin user4 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
       userController.createUserFromAdmin(user4);
        UserDtoFromAdmin user5 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user5);
        UserDtoFromAdmin user6 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user6);
        UserDtoFromAdmin user7 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user7);
        UserDtoFromAdmin user8 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user8);
        UserDtoFromAdmin user9 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        userController.createUserFromAdmin(user9);
        UserDtoFromAdmin user10 = new UserDtoFromAdmin("Hubert", "Wolfeschlegelsteinhausenbergerdorff","GUARDIAN");
        String createdUsername = userController.createUserFromAdmin(user10);

        assertTrue(createdUsername.length()<=30, "Username is not equal or shorter than 30 characters after creating 10 users with same long firstname and lastname");
    }
}

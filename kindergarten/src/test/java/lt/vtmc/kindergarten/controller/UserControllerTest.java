package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.UserDetailsDto;
import lt.vtmc.kindergarten.dto.UserDto;
import lt.vtmc.kindergarten.dto.UserDtoFromAdmin;
import lt.vtmc.kindergarten.service.UserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration
@WithMockUser(username="administratorius",roles={"ADMIN", "GUARDIAN"})
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Autowired
    PersonDao personDao;

//    @Test
//    @Order(1)
//    @Transactional
//    void testGettingUserWithAdminRole() {
//        String role = userController.getUser("administratorius").getRole();
//        assertEquals("ADMIN", role);
//    }

//    @Test
//    @Order(2)
//    void testGettingListOfAllUsersWithSize1() {
//        assertEquals(1, userController.getUsers().size());
//    }
//
//    @Test
//    @Order(3)
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void testCreatingUserAndGettingIt() {
//        UserDto user = new UserDto("ArnasJocys1",  "ArnasJocys1", "GUARDIAN");
//
//        userController.createUser(user);
//
//        assertNotEquals(null, userController.getUser("ArnasJocys1"));
//    }
//
//    @Test
//    @Order(4)
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void testCreatingEduSpecialistAndGettingIt() {
//        UserDto user = new UserDto("ArnasJocys2", "ArnasJocys2", "EDUCATION_SPECIALIST");
//
//        userController.createUser(user);
//
//        assertEquals("EDUCATION_SPECIALIST", userController.getUser("ArnasJocys2").getRole());
//    }
//
//    @Test
//    @Order(5)
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void testCreating2AdditionalUsersAndGettingListOfAllUsersWithSize3() {
//        UserDto user1 = new UserDto("ArnasJocys1", "ArnasJocys1", "GUARDIAN");
//        userController.createUser(user1);
//        UserDto user2 = new UserDto("ArnasJocys2",  "ArnasJocys2", "EDUCATION_SPECIALIST");
//        userController.createUser(user2);
//
//        assertEquals(3, userController.getUsers().size());
//    }

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

//    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void testAdminCreatingGuardianWithLowerCaseNamesGivesPascalCaseUsernameAndNames() {
//        UserDtoFromAdmin user = new UserDtoFromAdmin("arnas", "jocys","GUARDIAN");
//        String createdUsername = userController.createUserFromAdmin(user);
//
//        assertEquals("ArnasJocys1", userController.getUser(createdUsername).getUsername(), "Pascal case Username is not created");
////FIXME those must be moved to Person test
////        assertEquals("Arnas", userController.getUser(createdUsername).getFirstName(), "Pascal case firstname is not created");
////        assertEquals("Jocys", userController.getUser(createdUsername).getLastName(), "Pascal case lastname is not created");
//    }
//
//    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void testAdminCannotCreateSecondEducationSpecialist() {
//        UserDtoFromAdmin user = new UserDtoFromAdmin("Arnas", "Jocys","EDUCATION_SPECIALIST");
//        userController.createUserFromAdmin(user);
//        UserDtoFromAdmin user2 = new UserDtoFromAdmin("Li", "Yu","EDUCATION_SPECIALIST");
//        String createdUsername2 = userController.createUserFromAdmin(user2);
//
//        assertEquals("Švietimo specialistas jau egzistuoja. Prisijungimo vardas: ŠvietimoSpecialistas1", createdUsername2, "Trying to create second education specialist does not give info message that education specialist is already created");
//    }

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

    @Test
    public void testGetUserPersonalInfoByUsernameIfPersonDoesntExist() {
        ResponseEntity<?> userDetails = userController.getUserDetails("nonexistant");
        assertTrue(userDetails.getStatusCode()==HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetUserPersonalInfoByUsernameIfPersonExists() {
        Person person = new Person();
        person.setPersonalCode("12345678910");
        person.setEmail("katinai@miauksas.com");
        person.setFirstName("Katinas");
        person.setLastName("Patinas");
        person.setPostalCode("10321");
        person.setCity(CityEnum.VILNIUS);
        person.setPhoneNumber("+37065365887");
        person.setAddress("Katinu 15");
        person.setTribeId("perdaugkaciu");
        personDao.save(person);

        Role role = new Role(RoleType.GUARDIAN);
        User user = new User("KatinasPatinas1","");
        user.setRole(role);
        role.addUser(user);

        person.setUser(user);

        personDao.save(person);

        ResponseEntity<?> userDetails = userController.getUserDetails("KatinasPatinas1");
        assertTrue(userDetails.getStatusCode()==HttpStatus.OK);
        UserDetailsDto userDetailsBodyContents = (UserDetailsDto) userDetails.getBody();
        assertTrue(userDetailsBodyContents.getPersonDetails().getFirstName()=="Katinas");
        assertTrue(userDetailsBodyContents.getPersonDetails().getLastName()=="Patinas");
        assertTrue(userDetailsBodyContents.getPersonDetails().getPersonalCode()=="12345678910");
        assertTrue(userDetailsBodyContents.getPersonDetails().getEmail()=="katinai@miauksas.com");
        assertTrue(userDetailsBodyContents.getPersonDetails().getPostalCode()=="10321");
        assertTrue(userDetailsBodyContents.getPersonDetails().getCityEnum()==CityEnum.VILNIUS);
        assertTrue(userDetailsBodyContents.getPersonDetails().getAddress()=="Katinu 15");
        assertTrue(userDetailsBodyContents.getPersonDetails().getPhoneNumber()=="+37065365887");
    }

}

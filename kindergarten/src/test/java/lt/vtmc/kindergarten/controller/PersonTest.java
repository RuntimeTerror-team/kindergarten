package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.dao.UserDao;
import lt.vtmc.kindergarten.domain.*;
import lt.vtmc.kindergarten.dto.PersonDto;
import lt.vtmc.kindergarten.dto.PersonUserDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonTest {

    @Autowired
    PersonDao personDao;

    @Autowired
    PersonController personController;

    @Autowired
    UserDao userDao;

    @Test
    @Order(1)
    void testCreatePerson() {
        Person person = createPerson("12345678910");
        personDao.save(person);
        assertTrue(personDao.findAll().size()==1);
    }

    @Test
    @Order(2)
    void testAssignPersonToUser() {
        Person person = createPerson("12345678910");
        Role role = new Role(RoleType.GUARDIAN);
        User user = new User("KatinasPatinas1","");
        personDao.save(person);
        user.setRole(role);
        role.addUser(user);
        person.setUser(user);

        personDao.save(person);
        Person personFromDB = personDao.getOne(person.getId());
        assertTrue(user.getUsername() == personFromDB.getUser().getUsername(), "Should be assigned to same user");
    }

    @Test
    @Order(3)
    void testCreatePersonAndAssignUser() {
        PersonUserDto personDto = new PersonUserDto(new PersonDto(createPerson("12345678910")),"KatinasPatinas1");

        Role role = new Role(RoleType.GUARDIAN);
        User user = new User("KatinasPatinas1","");
        user.setRole(role);
        role.addUser(user);
        userDao.save(user);
        personController.addPersonWithUsername(personDto);

        User userEntity = userDao.findUserByUsername("KatinasPatinas1");
        Person person = personDao.findByUser(userEntity);
        assertTrue(person.getUser().getUsername()=="KatinasPatinas1");
    }

    private Person createPerson(String personalCode){
        Person person = new Person();
        person.setPersonalCode(personalCode);
        person.setEmail("katinai@miauksas.com");
        person.setFirstName("Katinas");
        person.setLastName("Patinas");
        person.setPostalCode("10321");
        person.setCity(CityEnum.VILNIUS);
        person.setPhoneNumber("+37065365887");

        return person;
    }


}

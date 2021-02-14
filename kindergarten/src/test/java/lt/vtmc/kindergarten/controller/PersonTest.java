package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.*;
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

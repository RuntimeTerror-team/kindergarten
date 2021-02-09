//package lt.vtmc.kindergarten.controller;
//
//import lt.vtmc.kindergarten.dao.PersonDao;
//import lt.vtmc.kindergarten.domain.CityEnum;
//import lt.vtmc.kindergarten.domain.Person;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class PersonTest {
//
//    @Autowired
//    PersonDao personDao;
//
//    @Test
//    @Order(1)
//    void testCreatePerson() {
//        Person person = new Person();
//        person.setId(1L);
//        person.setPersonalCode("39004180222");
//        person.setEmail("katinai@miauksas.com");
//        person.setFirstName("Katinas");
//        person.setLastName("Patinas");
//        person.setPersonalCode("123456789");
//        person.setPostalCode("10321");
//        person.setCity(CityEnum.VILNIUS);
//        person.setPhoneNumber("+37065365887");
//
//        personDao.save(person);
//
//    }
//
//}

package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.ApplicationDao;
import lt.vtmc.kindergarten.dao.PersonDao;
import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Disabled
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@Transactional
public class NewPersonStructurePocTest {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private ApplicationDao applicationDao;


    @Test
    @DisplayName("create age Range")
    void testCreatingAgeRange(){
        // Parent 1
        Person person1 = new Person();
        // Parent 2
        Person person2 = new Person();
        // Child 1
        Person person3 = new Person();

        Application application1 = new Application();


        application1.setParentId(person1);
        application1.setSecondParentId(person2);
        application1.setChildId(person3);


        applicationDao.save(application1);



        List<Person> persons = personDao.findAll();




    }
}

package lt.vtmc.kindergarten.controller;

import lt.vtmc.kindergarten.dao.ApplicationTestDao;
import lt.vtmc.kindergarten.dao.PersonTestDao;
import lt.vtmc.kindergarten.domain.ApplicationTest;
import lt.vtmc.kindergarten.domain.PersonTest;
import lt.vtmc.kindergarten.dto.AgeRangeDto;
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
    private PersonTestDao personTestDao;

    @Autowired
    private ApplicationTestDao applicationTestDao;


    @Test
    @DisplayName("create age Range")
    void testCreatingAgeRange(){
        // Parent 1
        PersonTest person1 = new PersonTest();
        // Parent 2
        PersonTest person2 = new PersonTest();
        // Child 1
        PersonTest person3 = new PersonTest();

        ApplicationTest application1 = new ApplicationTest();


        application1.setParentId(person1);
        application1.setSecondParentId(person2);
        application1.setChildId(person3);


        applicationTestDao.save(application1);



        List<PersonTest> persons = personTestDao.findAll();




    }
}

package lt.vtmc.kindergarten.controller;


import lt.vtmc.kindergarten.dto.QueueDtoWithOpeningDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@WithMockUser(username="administratorius",roles={"ADMIN", "EDUCATION_SPECIALIST"})
@DisplayName("When running Queue controller")
public class QueueControllerTest {

    @Autowired
    private QueueController queueController;

//    @Test
//    @DisplayName("create age Range")
//    void testAddQueueWithOpeningDate(){
//        QueueDtoWithOpeningDate queue = new QueueDtoWithOpeningDate();
//
//        queueController.addQueueOpeningDate(queue);
//
//        assertEquals(2, queueController.getQueues().get(0).getOpeningDate(), "should create queue with opening date correctly");
//
//
//    }
}

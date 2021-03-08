package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dto.LoggingDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LoggingService {

    @Autowired
    private EntityManager entityManager;



//    @Transactional
//    public List getLogs(){
//
//        List logs = entityManager.createNativeQuery("SELECT formatted_message FROM logging_event").getResultList();
//        List<LoggingDto> logsss = logs.stream().map(message -> {
//            return new LoggingDto(message).getLoggingMessage();
//        });
//        return logsss;
//    }
}

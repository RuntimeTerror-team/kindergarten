package lt.vtmc.kindergarten.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LoggingService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<String> getLogs(){
        List<String> logs = (List<String>) entityManager.createNativeQuery("SELECT formatted_message FROM logging_event").getResultList().stream().map(log -> (String) log).collect(Collectors.toList());
        return logs;
    }

}

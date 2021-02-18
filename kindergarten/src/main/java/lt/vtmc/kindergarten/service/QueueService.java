package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.QueueDao;
import lt.vtmc.kindergarten.domain.Queue;
import lt.vtmc.kindergarten.domain.QueueStatusEnum;
import lt.vtmc.kindergarten.dto.QueueDto;
import lt.vtmc.kindergarten.dto.QueueDtoFromAdmin;
import lt.vtmc.kindergarten.dto.QueueDtoFromEducationSpecialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@EnableScheduling
public class QueueService {

    @Autowired
    private QueueDao queueDao;


    @Transactional
    public void addQueueWithOpeningDate(QueueDtoFromAdmin queueDtoFromAdmin) {
        List<Queue> queues = queueDao.findAll();
        QueueStatusEnum queueStatusEnumToFind = QueueStatusEnum.ACTIVE;

        boolean isThereOtherActiveQueue =  queues.stream().anyMatch(queue -> queueStatusEnumToFind.equals(queue.getStatus()));

        if(!isThereOtherActiveQueue){
            Queue queue = new Queue();
            queue.setOpeningDate(queueDtoFromAdmin.getOpeningDate());
            queue.setStatus(QueueStatusEnum.ACTIVE);

            queueDao.save(queue);
        }
    }

    @Transactional
    public void updateQueueFromES(Long id, QueueDtoFromEducationSpecialist queueDto) {
        Queue queue = queueDao.getOne(id);
        queue.setRegistrationClosingDate(queueDto.getRegistrationClosingDate());
        queue.setClosingDate(queueDto.getClosingDate());

        queueDao.save(queue);
    }

    @Transactional(readOnly = true)
    public QueueDto getQueue(Long id) {
        Queue queue = queueDao.getOne(id);
        return new QueueDto(queue);
    }

    @Transactional(readOnly = true)
    public List<QueueDto> getQueues() {
        List<Queue> queues = queueDao.findAll(Sort.by(Sort.Direction.ASC, "openingDate"));
        List<QueueDto> queueList = queues.stream().map(queue -> new QueueDto(queue)).collect(Collectors.toList());
        return queueList;
    }

    /**
     * Checks for queue closing date and triggers lockdown if the deadline is met
     * Check happens every minute
     */
    @Scheduled(cron = "1 * * * * *")
    protected void lockQueueOnRegistrationClosingTime (){
        Queue queue = queueDao.findByStatus(QueueStatusEnum.ACTIVE);
        if(queue != null && queue.getRegistrationClosingDate()!=null && queue.getRegistrationClosingDate().before(new Date())){
            System.out.println("Queue is after due date. Closing queue.");
            queue.setStatus(QueueStatusEnum.LOCKED);
            queueDao.save(queue);
        }
    }
}

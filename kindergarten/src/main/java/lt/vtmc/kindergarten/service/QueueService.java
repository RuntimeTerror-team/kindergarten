package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.QueueDao;
import lt.vtmc.kindergarten.domain.Queue;
import lt.vtmc.kindergarten.dto.QueueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueueService {

    @Autowired
    private QueueDao queueDao;

    @Transactional
    public void addQueue(@Valid QueueDto queueDto) {

        Queue queue = new Queue();
        queue.setOpeningDate(queueDto.getOpeningDate());
        queue.setClosingDate(queueDto.getClosingDate());
        queue.setStatus(queueDto.getStatus());

        queueDao.save(queue);
    }


    @Transactional
    public void updateQueue(Long id, QueueDto queueDto) {
        Queue queue = queueDao.getOne(id);
        queue.setStatus(queueDto.getStatus());
        queue.setOpeningDate(queueDto.getOpeningDate());
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


}

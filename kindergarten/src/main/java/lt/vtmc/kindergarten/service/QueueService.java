package lt.vtmc.kindergarten.service;

import lt.vtmc.kindergarten.dao.QueueDao;
import lt.vtmc.kindergarten.domain.Queue;
import lt.vtmc.kindergarten.dto.QueueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

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
}

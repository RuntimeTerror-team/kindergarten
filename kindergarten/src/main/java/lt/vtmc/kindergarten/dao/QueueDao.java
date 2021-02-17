package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Queue;
import lt.vtmc.kindergarten.domain.QueueStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueDao extends JpaRepository<Queue, Long> {
    Queue findByStatus(QueueStatusEnum statusEnum);
}

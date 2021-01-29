package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<Group, Long> {
}

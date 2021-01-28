package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildDao extends JpaRepository<Child, Long> {
}

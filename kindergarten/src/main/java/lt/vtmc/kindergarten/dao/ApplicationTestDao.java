package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.ApplicationTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationTestDao extends JpaRepository<ApplicationTest, Long> {
}
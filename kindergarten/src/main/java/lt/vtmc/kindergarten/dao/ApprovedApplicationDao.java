package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovedApplicationDao extends JpaRepository<ApplicationAfterDistribution, Long> {
}

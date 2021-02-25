package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.HealthForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthFormRepository extends JpaRepository<HealthForm, String> {

    HealthForm getHealthFormByChildId(Long childId);
}

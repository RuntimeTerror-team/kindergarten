package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.HealthForm;
import lt.vtmc.kindergarten.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthFormRepository extends JpaRepository<HealthForm, String> {

    HealthForm getHealthFormByChildId(Long childId);

    @Query("SELECT h FROM HealthForm h WHERE h.child.id IN :childrenId")
    List<HealthForm> findAllByChildren(List<Long> childrenId);
}

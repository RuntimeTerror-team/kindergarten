package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.Group;
import lt.vtmc.kindergarten.domain.KindergartenApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindergartenApplicationFormDao  extends JpaRepository<KindergartenApplicationForm, Long> {
    void deleteByApplicationId(Long applicationId);
}

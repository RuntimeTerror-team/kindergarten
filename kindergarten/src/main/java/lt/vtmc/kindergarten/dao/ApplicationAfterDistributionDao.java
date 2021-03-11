package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationAfterDistributionDao extends JpaRepository<ApplicationAfterDistribution, Long> {
    ApplicationAfterDistribution findApplicationByApplicationId(Long applicationId);
    ApplicationAfterDistribution findApplicationByChildFirstNameAndChildLastName(String childFirstName, String childLastName);
    Page<ApplicationAfterDistribution> findByParentLastNameContaining(String parentLastName, Pageable pageable);
}

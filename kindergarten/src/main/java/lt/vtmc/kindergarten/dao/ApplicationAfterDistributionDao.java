package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;
import lt.vtmc.kindergarten.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationAfterDistributionDao extends JpaRepository<ApplicationAfterDistribution, Long> {
    ApplicationAfterDistribution findApplicationByApplicationId(Long applicationId);
    ApplicationAfterDistribution findApplicationByChildFirstNameAndChildLastName(String childFirstName, String childLastName);
}

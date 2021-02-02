package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.AgeRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRangeDao extends JpaRepository<AgeRange, Long> {
	
	AgeRange findByAgeMinAndAgeMax(int ageMin, int ageMax );

	
}

package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictDao extends JpaRepository<District,Long> {
}

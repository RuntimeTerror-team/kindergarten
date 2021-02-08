package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Kindergarten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindergartenDao extends JpaRepository<Kindergarten, Long> {
    Kindergarten findByCompanyCode(String companyCode);
}

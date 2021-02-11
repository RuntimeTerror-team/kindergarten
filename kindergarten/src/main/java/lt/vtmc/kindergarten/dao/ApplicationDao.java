package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDao extends JpaRepository<Application, Long> {
}
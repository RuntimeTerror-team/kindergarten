package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Application;
import lt.vtmc.kindergarten.domain.ApplicationStatusEnum;
import lt.vtmc.kindergarten.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ApplicationDao extends JpaRepository<Application, Long> {
    Application findApplicationByChild(Person child);
    Set<Application> findByParent(Person person);
    List<Application> findByApplicationStatus(ApplicationStatusEnum status);
}
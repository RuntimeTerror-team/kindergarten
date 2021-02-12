package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person,Long> {
}

package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Person;
import lt.vtmc.kindergarten.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person,Long> {
    Person findByUser(User user);
    Person findByPersonalCode(String personalCode);
}

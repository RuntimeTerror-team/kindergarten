package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Person;
import lt.vtmc.kindergarten.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person,Long> {
    Person findByUser(User user);
    Person findByPersonalCode(String personalCode);
    Person findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT p FROM Person p WHERE p.tribeId="
            + "(SELECT p.tribeId FROM Person p WHERE p.user.username = :username) "
            + "AND p.phoneNumber IS NULL")
    List<Person> getChildrenOfParentByUsername(String username);
}

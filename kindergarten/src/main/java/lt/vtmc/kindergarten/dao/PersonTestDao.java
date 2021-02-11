package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.PersonTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTestDao extends JpaRepository<PersonTest,Long> {
}

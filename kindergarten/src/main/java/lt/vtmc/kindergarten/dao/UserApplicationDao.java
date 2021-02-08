package lt.vtmc.kindergarten.dao;


import lt.vtmc.kindergarten.domain.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApplicationDao extends JpaRepository<UserApplication,Long> {
}

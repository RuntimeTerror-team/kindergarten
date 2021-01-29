package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String>  {
    User findUserByUsername(String username);

    User findByRole(Role role);
}

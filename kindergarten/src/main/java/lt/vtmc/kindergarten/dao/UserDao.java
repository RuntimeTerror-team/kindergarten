package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.Role;
import lt.vtmc.kindergarten.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String>  {
    User findByUsername(String username);

    User findByRole(Role role);

    User findUserByUsername(String username);


    @Query("FROM User b" + " WHERE b.username LIKE %:searchText% " + "ORDER BY b.username ASC")
    Page<User> findAllUsers(Pageable pageable, @Param("searchText") String searchText);
}

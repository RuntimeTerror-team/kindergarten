package lt.vtmc.kindergarten.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.kindergarten.domain.PermissionForES;

@Repository
public interface PermissionForESDao extends JpaRepository<PermissionForES, Long> {
	
}

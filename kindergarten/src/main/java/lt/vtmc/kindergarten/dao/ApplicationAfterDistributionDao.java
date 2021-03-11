package lt.vtmc.kindergarten.dao;

import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationAfterDistributionDao extends JpaRepository<ApplicationAfterDistribution, Long> {
    ApplicationAfterDistribution findApplicationByApplicationId(Long applicationId);
    ApplicationAfterDistribution findApplicationByChildFirstNameAndChildLastName(String childFirstName, String childLastName);
//    Page<ApplicationAfterDistribution> findByParentLastNameContaining(String parentLastName, Pageable pageable);

//    @Query("FROM ApplicationAfterDistribution b WHERE b.childLastName=:searchText OR b.childLastName=:searchText ORDER BY b.score DESC")
//    Page<ApplicationAfterDistribution> findAllApplications(Pageable pageable, @Param("searchText") String searchText);

    @Query("FROM ApplicationAfterDistribution b WHERE b.childLastName LIKE %:searchText% OR b.childFirstName LIKE %:searchText% OR b.parentFirstName LIKE %:searchText% OR b.parentLastName LIKE %:searchText% ORDER BY b.score ASC")
    Page<ApplicationAfterDistribution> findAllApplications(Pageable pageable, @Param("searchText") String searchText);
}

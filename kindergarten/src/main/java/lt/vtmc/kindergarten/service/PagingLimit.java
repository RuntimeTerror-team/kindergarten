package lt.vtmc.kindergarten.service;


import lt.vtmc.kindergarten.domain.ApplicationAfterDistribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagingLimit<T>  {
    Page<T> findAll(Pageable pageable);
    Page<T> findByParentLastNameContaining(String parentLastName, Pageable pageable);
}

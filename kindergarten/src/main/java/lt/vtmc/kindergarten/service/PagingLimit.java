package lt.vtmc.kindergarten.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagingLimit<T> {
    Page<T> findAll(Pageable pageable);
}

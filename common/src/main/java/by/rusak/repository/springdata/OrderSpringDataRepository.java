package by.rusak.repository.springdata;

import by.rusak.domain.hibernate.HibernateOrder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSpringDataRepository extends CrudRepository<HibernateOrder, Long>,
        JpaRepository<HibernateOrder, Long>, PagingAndSortingRepository<HibernateOrder, Long> {


}

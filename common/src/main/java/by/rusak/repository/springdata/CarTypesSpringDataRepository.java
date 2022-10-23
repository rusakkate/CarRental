package by.rusak.repository.springdata;

import by.rusak.domain.hibernate.HibernateCarTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypesSpringDataRepository extends JpaRepository<HibernateCarTypes, Long>,
        CrudRepository<HibernateCarTypes, Long>, PagingAndSortingRepository<HibernateCarTypes, Long> {
}

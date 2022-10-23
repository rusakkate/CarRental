package by.rusak.repository.springdata;

import by.rusak.domain.hibernate.HibernateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelsSpringDataRepository extends JpaRepository<HibernateModel, Long>,
        CrudRepository<HibernateModel, Long>, PagingAndSortingRepository<HibernateModel, Long> {
}

package by.rusak.repository.springdata;

import by.rusak.domain.hibernate.HibernateBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandSpringDataRepository extends JpaRepository<HibernateBrand, Long>,
        CrudRepository<HibernateBrand, Long>, PagingAndSortingRepository<HibernateBrand, Long> {

}

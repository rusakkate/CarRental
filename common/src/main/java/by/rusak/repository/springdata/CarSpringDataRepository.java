package by.rusak.repository.springdata;

import by.rusak.domain.hibernate.HibernateCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarSpringDataRepository extends JpaRepository<HibernateCar, Long>,
        CrudRepository<HibernateCar, Long>, PagingAndSortingRepository<HibernateCar, Long> {
}

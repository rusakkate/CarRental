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

    //@Query(value = "select * from carrental.orders", nativeQuery = true)
    //List<HibernateOrder> findHQLQueryNative();

    @Query ("select o from HibernateOrder o")
    List<HibernateOrder> findHQLQuery();
    @Query ("select o.id, o.idCar, o.idUser, o.orderPrice, o.routeDistance, o.route " +
            " from HibernateOrder o")
    List<Object[]> getPartsOfOrder();


}

package by.rusak.repository;

import by.rusak.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>,
        JpaRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {

    Page<Order>findOrdersByIdUser(Long idUser, Pageable pageable);

    Page<Order>findOrdersByIdCar(Long idCar, Pageable pageable);

    @Query(value = "select carrental.calculate_order_amount (:rental_start_date, :rental_end_date, :id_car)",
            nativeQuery = true)
    Double calculateOrderAmount (@Param("rental_start_date")Timestamp rentalStartDate,
                                 @Param("rental_end_date")Timestamp rentalEndDate,
                                 @Param("id_car") Long idCar
    );

    @Query(value = "select carrental.orders.rental_start_date, carrental.orders.rental_end_date, carrental.orders.order_price, " +
            "carrental.cars.brand, carrental.cars.model " +
            "from carrental.orders " +
            "join carrental.cars on carrental.orders.id_car = carrental.cars.id_car  " +
            "where carrental.cars.brand = :brand", nativeQuery = true)
    List<Object[]>findByHQLQueryNativeByBrand(@Param("brand") String brand);
}

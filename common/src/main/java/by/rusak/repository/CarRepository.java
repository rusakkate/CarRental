package by.rusak.repository;

import by.rusak.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List <Car> findCarsByBrand(String brand);

    List<Object[]> findCarByProductionYear (Integer prodYear);

    List <Object[]> findCarsByPriceDayBefore(Double priceDay);

    @Query(value = "select * from carrental.cars " +
            "where carrental.cars.brand = :brand", nativeQuery = true)
    List<Car> findByHQLQueryNativeCarByBrand(@Param("brand") String brand);


}

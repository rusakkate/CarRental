package by.rusak.repository;

import by.rusak.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, PagingAndSortingRepository<Car, Long> {

    Page <Car> findCarsByBrand(String brand, Pageable pageable);

    Page <Car> findCarsByBrandAndModel(String brand, String model, Pageable pageable);

    Page <Car> findCarByProductionYear (Integer prodYear, Pageable pageable);

    Page <Car> findCarsByPriceDayBefore(Double priceDay, Pageable pageable);

}

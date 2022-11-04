package by.rusak.service;

import by.rusak.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Page<Car> findAll(Pageable page);

    Car findById(Long carId);

    Page <Car> findCarsByBrand(String brand, Pageable pageable);

    Page <Car> findCarsByBrandAndModel(String brand, String model, Pageable pageable);

    Page <Car> findCarByProductionYear (Integer prodYear, Pageable pageable);

    Page <Car> findCarsByPriceDayBefore(Double priceDay, Pageable pageable);
}

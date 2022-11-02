package by.rusak.service;

import by.rusak.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();

    Optional<Car> findById(Long carId);

    List<Car> findCarsByBrand(String brand);

    List<Object[]> findCarByProductionYear(Integer productYear);

    List <Object[]> findCarsByPriceDayBefore(Double priceDay);
}

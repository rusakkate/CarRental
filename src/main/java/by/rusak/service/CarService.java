package by.rusak.service;

import by.rusak.domain.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarService {

    public List<Car> findAll();

    public List<Car> findAll(int limit, int offset);

    public Car create(Car object);

    public Car findById(Long id);

    public Car update(Car object);

    public Long delete(Long id);

    public List<Car> findTheHighestRatingCar();

    public Map<String, Object> getAvgCarRating();

    public List<Car> findCarRatingBelowAvg();

    public List<Car> findTheOldestCar();

    public List<Car> findTheCheapestCar();

    public Map<String, Object> getAvgPriceDay();

    public List<Car> findCarPriceDayBelowAvg();
}

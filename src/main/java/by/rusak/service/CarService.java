package by.rusak.service;

import by.rusak.domain.CarJdbc;

import java.util.List;
import java.util.Map;

public interface CarService {

    public List<CarJdbc> findAll();

    public List<CarJdbc> findAll(int limit, int offset);

    public CarJdbc create(CarJdbc object);

    public CarJdbc findById(Long id);

    public CarJdbc update(CarJdbc object);

    public Long delete(Long id);

    public List<CarJdbc> findTheHighestRatingCar();

    public Map<String, Object> getAvgCarRating();

    public List<CarJdbc> findCarRatingBelowAvg();

    public List<CarJdbc> findTheOldestCar();

    public List<CarJdbc> findTheCheapestCar();

    public Map<String, Object> getAvgPriceDay();

    public List<CarJdbc> findCarPriceDayBelowAvg();
}

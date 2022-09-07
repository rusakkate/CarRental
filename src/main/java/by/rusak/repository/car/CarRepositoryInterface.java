package by.rusak.repository.car;

import by.rusak.domain.Car;
import by.rusak.domain.Client;
import by.rusak.repository.CRUDRepository;

import java.util.List;
import java.util.Map;

public interface CarRepositoryInterface extends CRUDRepository<Long, Car> {

    public List<Car> findTheHighestRatingCar();
    Map<String, Object> getAvgCarRating();
    public List<Car> findCarRatingBelowAvg();
    public List<Car> findTheOldestCar();
    public List<Car> findTheCheapestCar();
    Map<String, Object> getAvgPriceDay();
    public List<Car> findCarPriceDayBelowAvg();


}

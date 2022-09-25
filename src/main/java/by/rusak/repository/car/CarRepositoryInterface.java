package by.rusak.repository.car;

import by.rusak.domain.CarJdbc;
import by.rusak.repository.CRUDRepository;

import java.util.List;
import java.util.Map;

public interface CarRepositoryInterface extends CRUDRepository<Long, CarJdbc> {

    public List<CarJdbc> findTheHighestRatingCar();
    Map<String, Object> getAvgCarRating();
    public List<CarJdbc> findCarRatingBelowAvg();
    public List<CarJdbc> findTheOldestCar();
    public List<CarJdbc> findTheCheapestCar();
    Map<String, Object> getAvgPriceDay();
    public List<CarJdbc> findCarPriceDayBelowAvg();


}

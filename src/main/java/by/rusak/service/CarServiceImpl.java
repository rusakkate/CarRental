package by.rusak.service;

import by.rusak.domain.CarJdbc;
import by.rusak.repository.car.CarRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
@Primary
public class CarServiceImpl implements CarService {

    private final CarRepositoryInterface carRepository;

    @Override
    public List<CarJdbc> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<CarJdbc> findAll(int limit, int offset) {
        return carRepository.findAll();
    }

    @Override
    public CarJdbc create(CarJdbc object) {
        return carRepository.create(object);
    }

    @Override
    public CarJdbc findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public CarJdbc update(CarJdbc object) {
        return carRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return carRepository.delete(id);
    }

    @Override
    public List<CarJdbc> findTheHighestRatingCar() {
        return carRepository.findTheHighestRatingCar();
    }

    @Override
    public Map<String, Object> getAvgCarRating() {
        return carRepository.getAvgCarRating();
    }

    @Override
    public List<CarJdbc> findCarRatingBelowAvg() {
        return carRepository.findCarRatingBelowAvg();
    }

    @Override
    public List<CarJdbc> findTheOldestCar() {
        return carRepository.findTheOldestCar();
    }

    @Override
    public List<CarJdbc> findTheCheapestCar() {
        return carRepository.findTheCheapestCar();
    }

    @Override
    public Map<String, Object> getAvgPriceDay() {
        return carRepository.getAvgPriceDay();
    }

    @Override
    public List<CarJdbc> findCarPriceDayBelowAvg() {
        return carRepository.findCarPriceDayBelowAvg();
    }
}

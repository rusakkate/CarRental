package by.rusak.service;

import by.rusak.domain.Car;
import by.rusak.repository.car.CarRepositoryInterface;
import by.rusak.repository.client.ClientRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Primary
public class CarServiceImpl implements CarService {

    private final CarRepositoryInterface carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findAll(int limit, int offset) {
        return carRepository.findAll();
    }

    @Override
    public Car create(Car object) {
        return carRepository.create(object);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car update(Car object) {
        return carRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return carRepository.delete(id);
    }

    @Override
    public List<Car> findTheHighestRatingCar() {
        return carRepository.findTheHighestRatingCar();
    }

    @Override
    public Map<String, Object> getAvgCarRating() {
        return carRepository.getAvgCarRating();
    }

    @Override
    public List<Car> findCarRatingBelowAvg() {
        return carRepository.findCarRatingBelowAvg();
    }

    @Override
    public List<Car> findTheOldestCar() {
        return carRepository.findTheOldestCar();
    }

    @Override
    public List<Car> findTheCheapestCar() {
        return carRepository.findTheCheapestCar();
    }

    @Override
    public Map<String, Object> getAvgPriceDay() {
        return carRepository.getAvgPriceDay();
    }

    @Override
    public List<Car> findCarPriceDayBelowAvg() {
        return carRepository.findCarPriceDayBelowAvg();
    }
}

package by.rusak.service;

import by.rusak.domain.Car;
import by.rusak.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository repository;

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Car> findById(Long carId) {
        return repository.findById(carId);
    }

    @Override
    public List<Car> findCarsByBrand(String brand) {
        return repository.findCarsByBrand(brand);
    }

    @Override
    public List<Object[]> findCarByProductionYear(Integer productYear) {
        return repository.findCarByProductionYear(productYear);
    }

    @Override
    public List<Object[]> findCarsByPriceDayBefore(Double priceDay) {
        return repository.findCarsByPriceDayBefore(priceDay);
    }
}

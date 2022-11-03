package by.rusak.service;

import by.rusak.domain.Car;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.repository.CarRepository;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public Car findById(Long carId) {
        return repository.findById(carId).orElseThrow(() ->
                new NoSuchEntityException("Car does not exist", 404, UUIDGenerator.generateUUID()));
    }

    @Override
    public List<Car> findCarsByBrand(String brand) {
        try {
            return repository.findCarsByBrand(brand);
        } catch (EntityNotFoundException e) {
            throw new NoSuchEntityException("Brand does not exist", 404, UUIDGenerator.generateUUID());
        }
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

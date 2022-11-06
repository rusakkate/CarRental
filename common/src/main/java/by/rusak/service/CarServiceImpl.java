package by.rusak.service;

import by.rusak.domain.Car;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.repository.CarRepository;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository repository;

    @Override
    public Page<Car> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Car findById(Long carId) {
        return repository.findById(carId).orElseThrow(() ->
                new NoSuchEntityException("Car with id " + carId + " does not exist", 404, UUIDGenerator.generateUUID()));
    }

    @Override
    public Page <Car> findCarsByBrand(String brand, Pageable pageable) {
        Page <Car> cars = repository.findCarsByBrand(brand, pageable);
        if (!cars.isEmpty()) {
            return cars;
        } else {
            throw new NoSuchEntityException("Brand " + brand + " does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public List<Car> findCarsByBrand(String brand) {
       try {
           return repository.findCarsByBrand(brand);
       } catch (EntityNotFoundException e) {
           throw new NoSuchEntityException("Brand " + brand + " does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public Page <Car> findCarsByBrandAndModel(String brand, String model, Pageable pageable) {
        Page <Car> cars = repository.findCarsByBrandAndModel(brand, model, pageable);
        if (!cars.isEmpty()) {
            return cars;
        } else {
            throw new NoSuchEntityException("Car with brand " + brand + " and model "
                    + model + " does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public  Page <Car> findCarByProductionYear (Integer prodYear, Pageable pageable) {
        Page <Car> cars = repository.findCarByProductionYear(prodYear, pageable);
        if (!cars.isEmpty()) {
            return cars;
        } else {
            throw new NoSuchEntityException("Car with product year " + prodYear
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public Page <Car> findCarsByPriceDayBefore(Double priceDay, Pageable pageable) {
        Page <Car> cars = repository.findCarsByPriceDayBefore(priceDay, pageable);
        if (!cars.isEmpty()) {
            return cars;
        } else {
            throw new NoSuchEntityException("Car with price before " + priceDay
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        }
    }
}

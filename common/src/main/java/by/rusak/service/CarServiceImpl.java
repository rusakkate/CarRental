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
                new NoSuchEntityException("Car does not exist", 404, UUIDGenerator.generateUUID()));
    }

    @Override
    public Page <Car> findCarsByBrand(String brand, Pageable pageable) {
        return repository.findCarsByBrand(brand, pageable);
    }

    @Override
    public Page <Car> findCarsByBrandAndModel(String brand, String model, Pageable pageable) {
        return repository.findCarsByBrandAndModel(brand, model, pageable);
    }

    @Override
    public   Page <Car> findCarByProductionYear (Integer prodYear, Pageable pageable) {
        return repository.findCarByProductionYear(prodYear, pageable);
    }

    @Override
    public Page <Car> findCarsByPriceDayBefore(Double priceDay, Pageable pageable) {
        return repository.findCarsByPriceDayBefore(priceDay, pageable);
    }


}

package by.rusak.service;

import by.rusak.domain.Order;
import by.rusak.domain.User;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.repository.OrderRepository;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final CarService carService;

    private final UserService userService;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchEntityException("Order does not exist", 404, UUIDGenerator.generateUUID()));
    }

    @Override
    public List<Object[]> findByBrand(String brand) {
        try {
            return repository.findByHQLQueryNativeByBrand(brand);
        } catch (EntityNotFoundException e) {
            throw new NoSuchEntityException("Brand does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public List<Object[]> findOrdersByModel(String model) {
        try {
            return repository.findByHQLQueryNativeOrdersByModel(model);
        } catch (EntityNotFoundException e) {
            throw new NoSuchEntityException("Brand does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public List<Order> findOrdersByIdUser(Long idUser) {
        try {
            return repository.findOrdersByIdUser(idUser);
        } catch (EntityNotFoundException e) {
            throw new NoSuchEntityException("User does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public List<Order> findOrdersByIdCar(Long idCar) {
        try {
            return repository.findOrdersByIdCar(idCar);
        } catch (EntityNotFoundException e) {
            throw new NoSuchEntityException("Car does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public Double calculateOrderAmount(Timestamp rentalStartDate, Timestamp rentalEndDate, Long idCar) {
        try {
            return repository.calculateOrderAmount(rentalStartDate, rentalEndDate, idCar);
        } catch (EntityNotFoundException e) {
            throw new NoSuchEntityException("Car does not exist", 404, UUIDGenerator.generateUUID());
        }
    }
}

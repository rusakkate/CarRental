package by.rusak.service;

import by.rusak.domain.Order;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.repository.OrderRepository;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final CarService carService;

    private  final UserService userService;

    @Override
    public Page<Order> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchEntityException("Order with id " + id
                        + " does not exist", 404, UUIDGenerator.generateUUID()));
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public Page<Order> findOrdersByIdUser(Long idUser, Pageable page) {
        if (userService.findById(idUser).equals(Exception.class)) {
            throw new NoSuchEntityException("User with id " + idUser
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        } else if (repository.findOrdersByIdUser(idUser, page).isEmpty()) {
            throw new NoSuchEntityException("User with id " + idUser
                    + " hasn't orders", 404, UUIDGenerator.generateUUID());
        } else {
            return repository.findOrdersByIdUser(idUser, page);
        }
    }

    @Override
    public Page<Order> findOrdersByIdCar(Long idCar, Pageable page) {
        if (carService.findById(idCar).equals(Exception.class)) {
            throw new NoSuchEntityException("Car with id " + idCar
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        } else if (repository.findOrdersByIdCar(idCar, page).isEmpty()) {
            throw new NoSuchEntityException("Car with id " + idCar
                    + " had not been ordered", 404, UUIDGenerator.generateUUID());
        } else {
            return repository.findOrdersByIdCar(idCar, page);
        }
    }

    @Override
    public List<Object[]> findByBrand(String brand) {
        if(carService.findCarsByBrand(brand).isEmpty()) {
            throw new NoSuchEntityException("Brand " + brand + " does not exist", 404, UUIDGenerator.generateUUID());
        } else if (repository.findByHQLQueryNativeByBrand(brand).isEmpty()){
            throw new NoSuchEntityException("Orders with brand " + brand
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        } else {
            return repository.findByHQLQueryNativeByBrand(brand);
        }
    }

    @Override
    public Double calculateOrderAmount(Timestamp rentalStartDate, Timestamp rentalEndDate, Long idCar) {
        return repository.calculateOrderAmount(rentalStartDate, rentalEndDate, idCar);
    }
}

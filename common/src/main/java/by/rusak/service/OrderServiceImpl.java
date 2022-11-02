package by.rusak.service;

import by.rusak.domain.Order;
import by.rusak.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Object[]> findByBrand(String brand) {
        return repository.findByHQLQueryNativeByBrand(brand);
    }

    @Override
    public List<Object[]> findOrdersByModel(String model) {
        return repository.findByHQLQueryNativeOrdersByModel(model);
    }

    @Override
    public List<Order> findOrdersByIdUser(Long idUser) {
        return repository.findOrdersByIdUser(idUser);
    }

    @Override
    public List<Order> findOrdersByIdCar(Long idCar) {
        return repository.findOrdersByIdCar(idCar);
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public Double calculateOrderAmount (Timestamp rentalStartDate, Timestamp rentalEndDate, Long idCar) {
        return repository.calculateOrderAmount(rentalStartDate, rentalEndDate, idCar);
    }
}

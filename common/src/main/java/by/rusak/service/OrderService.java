package by.rusak.service;

import by.rusak.domain.Order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> findOrderById(Long id);

    List<Object[]>findByBrand(String brand);

    List<Object[]>findOrdersByModel(String model);

    List<Order> findOrdersByIdUser(Long idUser);

    List<Order>findOrdersByIdCar(Long idCar);

    Order save(Order order);

    Double calculateOrderAmount (Timestamp rentalStartDate, Timestamp rentalEndDate,Long idCar);


}

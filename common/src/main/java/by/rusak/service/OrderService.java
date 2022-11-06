package by.rusak.service;

import by.rusak.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    Page<Order> findAll(Pageable page);

    List<Order> findAll();

    Order findOrderById(Long id);

    Page<Order> findOrdersByIdUser(Long idUser, Pageable pageable);

    Page<Order>findOrdersByIdCar(Long idCar, Pageable pageable);

    List<Object[]>findByBrand(String brand);

    Order save(Order order);

    Double calculateOrderAmount (Timestamp rentalStartDate, Timestamp rentalEndDate,Long idCar);


}

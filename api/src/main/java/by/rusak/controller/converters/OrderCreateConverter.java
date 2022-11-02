package by.rusak.controller.converters;

import by.rusak.controller.requests.OrderCreateRequest;
import by.rusak.domain.Order;
import by.rusak.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class OrderCreateConverter extends OrderBaseConverter <OrderCreateRequest, Order> {

    private final OrderService service;

    @Override
    public Order convert(OrderCreateRequest source) {

            Order order = new Order();

            order.setOrderPrice(service.calculateOrderAmount(source.getRentalEndDate(),
                    source.getRentalStartDate(),source.getIdCar()));
            order.setCreationDate(new Timestamp(new Date().getTime()));

            return doConvert(order, source);

    }

}

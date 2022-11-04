package by.rusak.controller.converters;

import by.rusak.controller.requests.OrderCreateRequest;
import by.rusak.domain.Order;
import by.rusak.security.AuthUserInformation;
import by.rusak.security.util.PrincipalUtil;
import by.rusak.service.OrderService;
import by.rusak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
                source.getRentalStartDate(), source.getIdCar()));
        order.setCreationDate(new Timestamp(new Date().getTime()));

        return doConvert(order, source);

    }

}

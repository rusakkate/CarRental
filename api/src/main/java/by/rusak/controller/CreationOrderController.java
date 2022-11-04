package by.rusak.controller;

import by.rusak.controller.requests.OrderCreateRequest;
import by.rusak.domain.Order;
import by.rusak.security.util.PrincipalUtil;
import by.rusak.service.CarService;
import by.rusak.service.OrderService;
import by.rusak.service.ScheduleService;
import by.rusak.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/createord")
public class CreationOrderController {
    private final ConversionService converter;

    private final ScheduleService scheduleService;

    private final OrderService orderService;

    private final CarService carService;

    private final UserService userService;

    @ApiOperation(value = "Create order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @PostMapping
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,
            timeout = 300, rollbackFor = Exception.class)
    public ResponseEntity<Object> createorder(@Valid @RequestBody OrderCreateRequest request, @ApiIgnore Principal principal) {

        Order order = converter.convert(request, Order.class);
        Map<String, Object> model = new HashMap<>();
        Timestamp maxUseDay = scheduleService.findMaxUseDay();

        if (order.getRentalStartDate().after(maxUseDay) || order.getRentalEndDate().after(maxUseDay)) {
            model.put("message", "The schedule for the selected period is not yet available");
        } else if (order.getRentalEndDate().before(order.getRentalStartDate())) {
            model.put("message", "Rental end date can't be before rental start day, try again");
        } else if (checkFreePeriod(order)) {
            model.put("message", "The car " + order.getIdCar() + " is busy on the selected dates, try again");
        } else {
            String login = PrincipalUtil.getUsername(principal);
            Long idUser = userService.findByCredentialsLogin(login).get().getId();
            order.setIdUser(idUser);
            Order createdOrder = orderService.save(order);
            setIsFreeFalse(order);
            model.put("message", "Order created");
            model.put("order", orderService.findOrderById(createdOrder.getId()));
        }

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    private int setIsFreeFalse(Order order) {
        return scheduleService.changeIsFreeToBusy(order.getIdCar(),
                order.getRentalStartDate(), order.getRentalEndDate());
    }

    private boolean checkFreePeriod(Order order) {
        return scheduleService.checkFreePeriod(carService.findById(order.getIdCar()).getId(),
                order.getRentalStartDate(), order.getRentalEndDate());
    }
}

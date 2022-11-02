package by.rusak.controller;


import by.rusak.domain.Order;
import by.rusak.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/ord")
public class AdminOrderController {
    private final OrderService service;

    @ApiOperation(value = "Finding all orders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping
    public ResponseEntity<Object> findAllOrd() {
        List<Order> ords = service.findAll();
        Map<String, List<Order>> result = Collections.singletonMap("result", ords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding order by order id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findOrdById(@PathVariable Long id) {
        Optional<Order> ord = service.findOrderById(id);
        Map<String, Optional<Order>> result = Collections.singletonMap("result", ord);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding orders by brand")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/ord/{brand}")
    public ResponseEntity<Object> findOrdByBrand(@PathVariable String brand) {
        List<Object[]> ords = service.findByBrand(brand);
        Map<String, List<Object[]>> result = Collections.singletonMap("result", ords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user's orders by user id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/orduser/{id}")
    public ResponseEntity<Object> findOrdByUserId(@PathVariable Long id) {
        List<Order> ords = service.findOrdersByIdUser(id);
        Map<String, List<Order>> result = Collections.singletonMap("result", ords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding orders by car id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/ordcar/{id}")
    public ResponseEntity<Object> findOrdByCarId(@PathVariable Long id) {
        List<Order> ords = service.findOrdersByIdCar(id);
        Map<String, List<Order>> result = Collections.singletonMap("result", ords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

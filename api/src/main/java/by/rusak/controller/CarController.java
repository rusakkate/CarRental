package by.rusak.controller;

import by.rusak.domain.Car;
import by.rusak.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService service;

    @GetMapping
    public ResponseEntity<Object> findAllCars() {
        List<Car> cars = service.findAll();
        Map<String, List<Car>> result = Collections.singletonMap("result", cars);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findCarById(@PathVariable Long id) {
        Optional<Car> cars = service.findById(id);
        Map<String, Object> model = new HashMap<>();
        if (cars.isEmpty()) {
            model.put("message", "Car with id " + id + " does not exist");
        } else {
            model.put("message", cars);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/productyear/{productyear}")
    public ResponseEntity<Object> findCarsByProdYear(@PathVariable Integer productyear) {
        List<Object[]> cars = service.findCarByProductionYear(productyear);
        Map<String, Object> model = new HashMap<>();
        if (cars.isEmpty()) {
            model.put("message", "Car with product year " + productyear + " does not exist");
        } else {
            model.put("message", cars);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/pricebefore/{price}")
    public ResponseEntity<Object> findCarsByPriceBefore(@PathVariable Double price) {
        List<Object[]> cars = service.findCarsByPriceDayBefore(price);
        Map<String, Object> model = new HashMap<>();
        if (cars.isEmpty()) {
            model.put("message", "Car with price before " + price + " does not exist");
        } else {
            model.put("message", cars);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/brand/{brand}")
    public ResponseEntity<Object> findCarsByBrand(@PathVariable String brand) {
        List<Car> cars = service.findCarsByBrand(brand);
        Map<String, Object> model = new HashMap<>();
        if (cars.isEmpty()) {
            model.put("message", "Car with brand " + brand + " does not exist");
        } else {
            model.put("message", cars);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}

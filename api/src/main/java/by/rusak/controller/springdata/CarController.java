package by.rusak.controller.springdata;

import by.rusak.domain.hibernate.HibernateCar;
import by.rusak.repository.springdata.CarSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/car")
public class CarController {
    private final CarSpringDataRepository repository;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        List<HibernateCar> listAllCar = repository.findAll();
        Map<String, List<HibernateCar>> result = Collections.singletonMap("result", listAllCar);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

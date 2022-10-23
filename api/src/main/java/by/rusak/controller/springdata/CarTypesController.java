package by.rusak.controller.springdata;


import by.rusak.domain.hibernate.HibernateCarTypes;
import by.rusak.repository.springdata.CarTypesSpringDataRepository;
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
@RequestMapping("/rest/carTypes")
public class CarTypesController {
    private final CarTypesSpringDataRepository repository;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        List<HibernateCarTypes> listAllCarTypes = repository.findAll();
        Map<String, List<HibernateCarTypes>> result = Collections.singletonMap("result", listAllCarTypes);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

package by.rusak.controller.springdata;

import by.rusak.domain.hibernate.HibernateBrand;
import by.rusak.repository.springdata.BrandSpringDataRepository;
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
@RequestMapping("/rest/brand")
public class BrandController {

    private final BrandSpringDataRepository repository;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        List<HibernateBrand> listAllBrands = repository.findAll();
        Map<String, List<HibernateBrand>> result = Collections.singletonMap("result", listAllBrands);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

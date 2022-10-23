package by.rusak.controller.springdata;

import by.rusak.domain.hibernate.HibernateBrand;
import by.rusak.domain.hibernate.HibernateModel;
import by.rusak.repository.springdata.BrandSpringDataRepository;
import by.rusak.repository.springdata.ModelsSpringDataRepository;
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
@RequestMapping("/rest/model")
public class ModelController {

    private final ModelsSpringDataRepository repository;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        List<HibernateModel> listAllModels = repository.findAll();
        Map<String, List<HibernateModel>> result = Collections.singletonMap("result", listAllModels);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

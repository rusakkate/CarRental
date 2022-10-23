package by.rusak.controller.springdata;

import by.rusak.domain.hibernate.HibernateOrder;
import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.repository.springdata.OrderSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/orders")
public class OrderController {

    private final OrderSpringDataRepository repository;


    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        List<HibernateOrder> listAllOrders = repository.findAll();
        Map<String, List<HibernateOrder>> result = Collections.singletonMap("result", listAllOrders);

        return new ResponseEntity<>(result, HttpStatus.OK);

       /* PageRequest pageRequest = PageRequest.of(0, 1);
        Page<HibernateOrder> allOrders = repository.findAll(pageRequest);
        Map<String, Page<HibernateOrder>> result = Collections.singletonMap("result", allOrders);

        return new ResponseEntity<>(result, HttpStatus.OK);*/
    }
}

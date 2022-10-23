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
@RequestMapping("/rest/orders")
public class OrderController {

    private final OrderSpringDataRepository repository;


    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        List<HibernateOrder> listAllOrders = repository.findHQLQuery();
        Map<String, List<HibernateOrder>> result = Collections.singletonMap("result", listAllOrders);
        return new ResponseEntity<>(result, HttpStatus.OK);


        /*List<Object[]> listAllOrders = repository.getPartsOfOrder();
        Map<String, List<Object[]> > result = Collections.singletonMap("result", listAllOrders);
        return new ResponseEntity<>(result, HttpStatus.OK);*/

    }
}

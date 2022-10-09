package by.rusak.controller.springdata;

import by.rusak.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {
    private final UserSpringDataRepository repository;


    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 1))), HttpStatus.OK);
    }

    @GetMapping ("/test")
    public ResponseEntity<Object> testEndpointByName(@RequestParam("user_name") String userName){
        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findHibernateUserByUserName(userName)), HttpStatus.OK);
    }

}

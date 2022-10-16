package by.rusak.controller;

import by.rusak.controller.requests.UserCreateRequest;
import by.rusak.controller.requests.UserSearchRequest;
import by.rusak.domain.SearchCriteria;
import by.rusak.domain.User;
import by.rusak.repository.hibernate.HibernateUserInterface;
import by.rusak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/users")
public class UserRestController {

    private final UserService userService;

    private final HibernateUserInterface userRepository;


    @GetMapping
    @RequestMapping("/hibernate")
    public ResponseEntity<Object> findAllHibernateUsers() {

        //return new ResponseEntity<>(Collections.singletonMap("result", userRepository.getUserStats()), HttpStatus.OK);

        return new ResponseEntity<>(Collections.singletonMap("result", userRepository.findAll(1, 1)), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/hibernate/criteria")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllHibernateUsers(@ModelAttribute SearchCriteria criteria) {

        return new ResponseEntity<>(Collections.singletonMap("result", userRepository.criteriaAPITest(criteria)), HttpStatus.OK);

        //return Collections.singletonMap("result", userService.findAll());
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllUsers() {

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findAll()), HttpStatus.OK);

        //return Collections.singletonMap("result", userService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findAllUsersWithParams(@ModelAttribute UserSearchRequest userSearchRequest) {

        int verifiedLimit = Integer.parseInt(userSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(userSearchRequest.getOffset());

        List<User> users = userService.search(verifiedLimit, verifiedOffset);

        Map<String, Object> model = new HashMap<>();
        model.put("user", "kotik");
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findUserById(@PathVariable String id) {

        //We have added id parsing and number format checking
        long userId = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("user", userService.findById(userId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {

        User user = new User();
        user.setSurname(createRequest.getSurname());
        user.setUserName(createRequest.getUserName());
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setDriverLicenseNumber("DriverLicenseNumber");
        user.setDriverLicenseDate(new Timestamp(new Date().getTime()));
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);

        userService.create(user);

        List<User> users = userService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("user", user.getUserName());
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }


}


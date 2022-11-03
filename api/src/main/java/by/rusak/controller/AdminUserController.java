package by.rusak.controller;

import by.rusak.domain.User;
import by.rusak.repository.RoleRepository;
import by.rusak.repository.UserRepository;
import by.rusak.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
@RequestMapping("/admin/users")
public class AdminUserController {
    private final UserService service;

    @ApiOperation(value = "Finding all users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
    })
    @GetMapping
    public ResponseEntity<Object> findAllUsers() {
        List<User> users = service.findAll();
        Map<String, List<User>> result = Collections.singletonMap("result", users);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
    })
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable Long id) {
        User user = service.findById(id);
        Map<String, User> result = Collections.singletonMap("result", user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user's order by user id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
    })
    @GetMapping(value = "/userOrder/{id}")
    public ResponseEntity<Object> findUserOrder(@PathVariable Long id) {
        List<Object[]> userOrders = service.findUserOrders(id);
        Map<String, List<Object[]>> result = Collections.singletonMap("result", userOrders);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user's order by user name")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
    })
    @GetMapping(value = "/userOrder/{login}")
    public ResponseEntity<Object> findUserOrderByLogin(@PathVariable String login) {
        List<Object[]> userOrders = service.findByHQLQueryNativeUserOrdersByLogin(login);
        Map<String, List<Object[]>> result = Collections.singletonMap("result", userOrders);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

  /*  @ApiOperation(value = "Create user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
    })
    @PostMapping("/createUser")
    @Transactional
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateRequest createRequest) {

        User user = converter.convert(createRequest, User.class);
        User createdUser = repository.save(setRoles(user));

        Map<String, Object> model = new HashMap<>();
        model.put("user", repository.findById(createdUser.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    private User setRoles(User user) {
        Set<Role> roles = user.getRoles();

        Set<Role> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(rolesSpringDataRepository.findById(2L).get());

        user.setRoles(updatedRoles);

        return user;
    }
    */

}

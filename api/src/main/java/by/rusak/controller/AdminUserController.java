package by.rusak.controller;

import by.rusak.controller.requests.UserChangeDriverLicenseRequest;
import by.rusak.domain.Order;
import by.rusak.domain.User;
import by.rusak.repository.RoleRepository;
import by.rusak.repository.UserRepository;
import by.rusak.security.jwt.JwtTokenHelper;
import by.rusak.security.util.PrincipalUtil;
import by.rusak.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {
    private final UserService service;
    @ApiOperation(value = "Finding all users with Page Info response. Available for admin after logging, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token",
                    required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping
    public ResponseEntity<Page<User>> findAllUsers(@ApiIgnore Pageable pageable) {
        Page<User> users = service.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user by id. Available for admin after logging, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
    })
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable Long id) {
        User user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user's order by login. Available for admin after logging, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token",
                    required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/userOrder/{login}")
    public ResponseEntity<Object> findUserOrderByLogin(@PathVariable String login ) {
        List<Object[]> userOrders = service.findUserOrdersByLogin(login);
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @ApiOperation(value = "Change user status isDeleted on true or false. Available for admin after logging, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/changeStatus/{idUser}/{status}")
    public ResponseEntity<Object> updateDriverInfo (@PathVariable Long idUser, @PathVariable Boolean status) {
        User user = service.findById(idUser);
        user.setIsDeleted(status);
        service.update(user);
        return new ResponseEntity<>("User with id " + user.getId() + " status isDeleted changed on " + user.getIsDeleted(), HttpStatus.OK);
    }

}

package by.rusak.controller;

import by.rusak.domain.User;
import by.rusak.security.AuthUserInformation;
import by.rusak.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    private final AuthUserInformation authUserInformation;

    @ApiOperation(value = "Finding user's information")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping
    public ResponseEntity<Object> getUserInformation () {
        String login = authUserInformation.getAuthUserLogin();
        Optional<User> user = service.findByCredentialsLogin(login);
        Map<String,  Optional<User>> result = Collections.singletonMap("result", user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user's orders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/userOrders")
    public ResponseEntity<Object> getUserOrders () {
        String login = authUserInformation.getAuthUserLogin();
        List<Object[]> userOrders = service.findByHQLQueryNativeUserOrdersByLogin(login);
        Map<String, List<Object[]>> result = Collections.singletonMap("result", userOrders);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

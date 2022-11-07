package by.rusak.controller;

import by.rusak.controller.requests.UserChangeDriverLicenseRequest;
import by.rusak.controller.requests.UserChangePasswordRequest;
import by.rusak.domain.User;
import by.rusak.security.util.PrincipalUtil;
import by.rusak.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@ApiModel(description = "User's personal account. Available after logging into account, link /auth")
public class UserController {
    private final UserService service;

    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "User's information. Available after logging into account, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping
    public ResponseEntity<Object> getUserInformation1 (@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        Optional<User> user = service.findByCredentialsLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "User order history. Available after logging into account, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/userOrders")
    public ResponseEntity<Object> getUserOrders (@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        List<Object[]> userOrders = service.findUserOrdersByLogin(login);
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @ApiOperation(value = "Change information about driver license. Available after logging into account, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @PostMapping(value = "/updateDriverInfo")
    public ResponseEntity<Object> updateDriverInfo (@ApiIgnore Principal principal,
                                                 @Valid @RequestBody UserChangeDriverLicenseRequest userRequest) {
        String login = PrincipalUtil.getUsername(principal);
        Optional<User> user = service.findByCredentialsLogin(login);
        Optional<User> updatedUser = updateDriverInfo (user, userRequest);

        Map<String, Object> model = new HashMap<>();
        model.put("message", "User information change");
        model.put("new user driver license number", updatedUser.get().getDriverLicenseNumber());
        model.put("new user driver license date", updatedUser.get().getDriverLicenseDate());
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @ApiOperation(value = "Change password. Available after logging into account, link /auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string")
    })
    @PostMapping(value = "/changePassword")
    public ResponseEntity<Object> changePassword (@ApiIgnore Principal principal,
                                                    @Valid @RequestBody UserChangePasswordRequest userRequest) {
        String login = PrincipalUtil.getUsername(principal);
        Optional<User> user = service.findByCredentialsLogin(login);
        updateCredentials (user, userRequest);
        Map<String, Object> model = new HashMap<>();
        model.put("message", "User password has been changed");
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    private Optional<User> updateDriverInfo (Optional <User> userForUpdate, UserChangeDriverLicenseRequest userRequest) {
        userForUpdate.get().setDriverLicenseNumber(userRequest.getDriverLicenseNumber());
        userForUpdate.get().setModificationDate(userRequest.getDriverLicenseDate());
        userForUpdate.get().setModificationDate(new Timestamp(new Date().getTime()));
        service.update(userForUpdate.get());
        return userForUpdate;
    }

    private Optional<User> updateCredentials (Optional <User> userForUpdate, UserChangePasswordRequest userRequest) {
        userForUpdate.get().getCredentials().setPassword(passwordEncoder.encode(userRequest.getUserPassword()));
        service.update(userForUpdate.get());
        return userForUpdate;
    }

}

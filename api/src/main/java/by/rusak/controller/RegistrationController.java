package by.rusak.controller;

import by.rusak.controller.requests.UserRegistrationRequest;
import by.rusak.domain.User;
import by.rusak.service.EmailService;
import by.rusak.service.UserRoleService;
import by.rusak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final ConversionService converter;

    private final UserService userService;

    private final EmailService emailService;

    private final UserRoleService userRoleService;

    @PostMapping
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,
            timeout = 300, rollbackFor = Exception.class)
    public ResponseEntity<Object> registration (@Valid @RequestBody UserRegistrationRequest registrationRequest) {

        User user = converter.convert(registrationRequest, User.class);
        Map<String, Object> model = new HashMap<>();
        HttpStatus httpStatus;

        if (userService.findByCredentialsLogin(user.getCredentials().getLogin()).isPresent()) {
            model.put("message", "User with such login exist");
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (userService.findByEmail(user.getEmail()).isPresent()){
            model.put("message", "User with such email exist");
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            user.setActivationCode(UUID.randomUUID().toString());
            userService.save(user);
            emailService.sendActivationCode(user);
            model.put("message", "Mail with the link to verify your registration send in your email");
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(model, httpStatus);
    }

    @GetMapping("/{code}")
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,
            timeout = 300, rollbackFor = Exception.class)
    public ResponseEntity<Object> activateUser (@PathVariable String code) {
        Map<String, Object> model = new HashMap<>();
        HttpStatus httpStatus;

        Optional <User> user = userService.findByActivationCode(code);

        if (user.isEmpty()) {
            model.put("message", "Unsuccessfully activate");
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            userService.activateUser(user.get());
            userRoleService.setUserRoles(user.get());
            httpStatus = HttpStatus.OK;
            model.put("message", "User activated");
            model.put("user", user);
        }

        return new ResponseEntity<>(model, httpStatus);
    }
}

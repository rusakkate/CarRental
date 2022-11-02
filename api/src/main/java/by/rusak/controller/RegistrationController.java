package by.rusak.controller;

import by.rusak.controller.requests.UserRegistrationRequest;
import by.rusak.domain.SystemRoles;
import by.rusak.domain.User;
import by.rusak.domain.UserRole;
import by.rusak.repository.UserRepository;
import by.rusak.service.RoleService;
import by.rusak.service.UserRoleService;
import by.rusak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final ConversionService converter;

    private final UserService userService;

    private final UserRepository repository;

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    @PostMapping
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED,
            timeout = 300, rollbackFor = Exception.class)
    public ResponseEntity<Object> registration(@Valid @RequestBody UserRegistrationRequest registrationRequest) {

        User user = converter.convert(registrationRequest, User.class);

        Map<String, Object> model = new HashMap<>();

        if (userService.checkForExistsLogin(user)) {
            model.put("message", "User with such login exist");
        } else if (userService.checkForExistsEmail(user)){
            model.put("message", "User with such email exist");
        }else {
            User createdUser = userService.save(user);
            setUserRoles(createdUser);
            model.put("message", "User created");
            model.put("user", userService.findById(createdUser.getId()).get());
        }

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    private void setUserRoles(User user) {
        UserRole userRole = new UserRole();
        userRole.setIdUser(user.getId());
        userRole.setIdRole(roleService.findRoleIdByRoleName(SystemRoles.ROLE_USER).getId());
        userRoleService.save(userRole);
    }
}

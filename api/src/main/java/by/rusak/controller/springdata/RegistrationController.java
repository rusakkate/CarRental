package by.rusak.controller.springdata;

import by.rusak.controller.requests.UserRegistrationRequest;
import by.rusak.domain.SystemRoles;
import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.domain.hibernate.HibernateUserRole;
import by.rusak.service.RoleService;
import by.rusak.service.UserRoleService;
import by.rusak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> registration(@Valid @RequestBody UserRegistrationRequest registrationRequest) {

        HibernateUser user = converter.convert(registrationRequest, HibernateUser.class);
        HibernateUser createdUser = userService.save(user);

        setUserRoles(createdUser);

        Map<String, Object> model = new HashMap<>();
        model.put("user", userService.findById(createdUser.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    private void setUserRoles(HibernateUser user) {
        HibernateUserRole userRole = new HibernateUserRole();
        userRole.setIdUser(user.getId());
        userRole.setIdRole(roleService.findRoleIdByRoleName(SystemRoles.ROLE_USER).getId());
        userRoleService.save(userRole);
    }

}

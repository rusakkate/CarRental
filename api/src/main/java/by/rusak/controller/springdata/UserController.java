package by.rusak.controller.springdata;

import by.rusak.domain.hibernate.HibernateRole;
import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.repository.springdata.RolesSpringDataRepository;
import by.rusak.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {
    private final UserSpringDataRepository repository;

    private final RolesSpringDataRepository rolesSpringDataRepository;

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

    @GetMapping ("/credentials")
    public ResponseEntity<Object> testEndpointByLogin(@RequestParam("user_login") String userLogin){
        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findByCredentialsLogin(userLogin)), HttpStatus.OK);
    }

    private HibernateUser setRoles(HibernateUser user) {
        Set<HibernateRole> roles = user.getRoles();

        Set<HibernateRole> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(rolesSpringDataRepository.findById(1L).get());
        updatedRoles.add(rolesSpringDataRepository.findById(2L).get());

        user.setRoles(updatedRoles);

        return user;
    }

}

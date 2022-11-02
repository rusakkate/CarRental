package by.rusak.controller;

import by.rusak.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/roles")
public class RolesController {

    private final RoleRepository repository;

    @GetMapping
    public ResponseEntity<Object> findAllRolesWithCache() {
        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAll()),
                HttpStatus.OK
        );
    }
}

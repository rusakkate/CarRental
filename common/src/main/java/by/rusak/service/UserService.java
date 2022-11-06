package by.rusak.service;

import by.rusak.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<User> findAll(Pageable page);

    Optional <User> findByCredentialsLogin (String login);

    Optional <User> findByEmail(String email);

    User save(User user);

    User update (User userForUpdate);

    User findById(Long userId);

    Optional <User> findByActivationCode(String activationCode);

    void activateUser (User user);

    List<Object[]> findUserOrdersByLogin(@Param("user_login") String login);

}

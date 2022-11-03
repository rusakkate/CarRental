package by.rusak.service;

//import by.rusak.domain.User;
import by.rusak.domain.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional <User> findByCredentialsLogin (String login);

    Optional <User> findByEmail(String email);

    List<Object[]> findUserOrders (Long id);

    User save(User user);

    User findById(Long userId);

/*    boolean checkForExistsLogin (User user);

    boolean checkForExistsEmail (User user);*/

    List<Object[]> findByHQLQueryNativeUserOrdersByLogin(@Param("user_login") String login);

}

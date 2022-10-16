package by.rusak.repository.user;

import by.rusak.domain.User;
import by.rusak.repository.CRUDRepository;
import org.springframework.security.access.annotation.Secured;

import java.util.Map;
import java.util.Optional;

public interface UserRepositoryInterface extends CRUDRepository<Long, User> {

    @Secured("ROLE_ADMIN")
    Map<String, Object> getUserStats();

    Optional<User> findByLogin(String login);

}
package by.rusak.repository.user;

import by.rusak.domain.User;
import by.rusak.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepositoryInterface extends CRUDRepository<Long, User> {

    Map<String, Object> getUserStats();

    Optional<User> findByLogin(String login);

}
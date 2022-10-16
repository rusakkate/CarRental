package by.rusak.service;

import by.rusak.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    Map<String, Object> getUserStats();

    User create(User object);

    User findById(Long userId);

    List<User> search(int limit, int offset);
}

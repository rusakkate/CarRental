package by.rusak.service;

import by.rusak.domain.User;
import by.rusak.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Map<String, Object> getUserStats();

    User create(User object);

    Optional<HibernateUser> findById(Long userId);

    List<User> search(int limit, int offset);

    HibernateUser save(HibernateUser hibernateUser);
}

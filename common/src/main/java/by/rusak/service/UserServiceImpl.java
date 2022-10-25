package by.rusak.service;

import by.rusak.domain.User;
import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.repository.springdata.UserSpringDataRepository;
import by.rusak.repository.user.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryInterface userRepository;

    private final UserSpringDataRepository repository;

    @Override
    public HibernateUser save(HibernateUser hibernateUser) {
        return repository.save(hibernateUser);
    }

    @Override
    public Optional<HibernateUser> findById(Long userId) {
        return repository.findById(userId);
    }


    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public Map<String, Object> getUserStats() {
        return userRepository.getUserStats();
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }


    @Override
    public List<User> search(int limit, int offset) {
        return userRepository.findAll(limit, offset);
    }



}


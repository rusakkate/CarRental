package by.rusak.service;

import by.rusak.domain.User;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.repository.UserRepository;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Page<User> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Optional<User> findByCredentialsLogin(String login) {return repository.findByCredentialsLogin(login);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);

    }

    @Override
    public User update(User userForUpdate) {
        repository.save(userForUpdate);
        return repository.findById(userForUpdate.getId()).orElseThrow(() ->
                new NoSuchEntityException("User does not exist", 404, UUIDGenerator.generateUUID()));
    }

    @Override
    public User findById(Long userId) {
        return repository.findById(userId).orElseThrow(() ->
                new NoSuchEntityException("User does not exist", 404, UUIDGenerator.generateUUID()));
    }

    @Override
    public Optional<User> findByActivationCode(String activationCode) {
        return repository.findByActivationCode(activationCode);
    }

    @Override
    public void activateUser(User user) {
        user.setActivationCode("");
        user.setIsEnabled(true);
    }

    @Override
    public List<Object[]> findUserOrdersByLogin(@Param("user_login") String login) {
        if (findByCredentialsLogin(login).isEmpty()) {
            throw new NoSuchEntityException("User with login " + login
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        } else if (repository.findByHQLQueryNativeUserOrdersByLogin(login).isEmpty()) {
            throw new NoSuchEntityException("User with login " + login
                    + " has not orders", 404, UUIDGenerator.generateUUID());
        } else {
            return repository.findByHQLQueryNativeUserOrdersByLogin(login);
        }
    }
}


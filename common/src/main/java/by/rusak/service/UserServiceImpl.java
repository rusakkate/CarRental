package by.rusak.service;

//import by.rusak.domain.User;

import by.rusak.domain.User;
import by.rusak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findByCredentialsLogin(String login) {
        return repository.findByCredentialsLogin(login);
    }

    @Override
    public List<Object[]> findUserOrders(Long id) {
        List<Object[]> userOrders = repository.findByHQLQueryNativeUserOrders(id);
            return userOrders;
    }

    @Override
    public User save(User user) {
        return repository.save(user);

    }

    @Override
    public Optional<User> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public boolean checkForExistsLogin(User user) {
        if (repository.findByCredentialsLogin(user.getCredentials().getLogin()).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkForExistsEmail(User user) {
        if (repository.findByEmail(user.getEmail())!=null) {
            return true;
        }
        return false;
    }


    @Override
    public List<Object[]> findByHQLQueryNativeUserOrdersByLogin(@Param("user_login") String login){
        return repository.findByHQLQueryNativeUserOrdersByLogin(login);
    }


}


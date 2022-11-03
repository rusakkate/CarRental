package by.rusak.service;

//import by.rusak.domain.User;

import by.rusak.domain.User;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.repository.UserRepository;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public Optional <User> findByEmail(String email)  {
        return repository.findByEmail(email);
    }

    @Override
    public List<Object[]> findUserOrders(Long id) {
       try {
           return repository.findByHQLQueryNativeUserOrders (id);
        } catch (IllegalStateException e) {
            throw new NoSuchEntityException("User does not exist", 404, UUIDGenerator.generateUUID());
        }
    }

    @Override
    public User save(User user) {
        return repository.save(user);

    }

    @Override
    public User findById(Long userId) {
        return repository.findById(userId).orElseThrow(() ->
                new NoSuchEntityException("User does not exist", 404, UUIDGenerator.generateUUID()));
    }

//    @Override
//    public boolean checkForExistsLogin(User user) {
//        if (repository.findByCredentialsLogin(user.getCredentials().getLogin()).isPresent()) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean checkForExistsEmail(User user) {
//        if (repository.findByEmail(user.getEmail())!=null) {
//            return true;
//        }
//        return false;
//    }


    @Override
    public List<Object[]> findByHQLQueryNativeUserOrdersByLogin(@Param("user_login") String login){
        try {
            return repository.findByHQLQueryNativeUserOrdersByLogin(login);
        } catch (IllegalStateException e) {
            throw new NoSuchEntityException("User does not exist", 404, UUIDGenerator.generateUUID());
        }
    }


}


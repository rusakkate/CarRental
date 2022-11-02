package by.rusak.controller.converters;

import by.rusak.controller.requests.UserChangeRequest;
import by.rusak.domain.User;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.service.UserService;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChangeConverter extends UserBaseConverter<UserChangeRequest, User> {

    private final UserService userService;

    @Override
    public User convert(UserChangeRequest source) {

        try {
            Optional<User> user = userService.findById(source.getId());
            return doConvert(user.get(), source);
        } catch (EntityNotFoundException e){
            throw new NoSuchEntityException("User does not exist", 404, UUIDGenerator.generateUUID());
        }

    }
}
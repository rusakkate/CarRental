package by.rusak.controller.converters;

import by.rusak.controller.requests.UserRegistrationRequest;
import by.rusak.domain.Credentials;
import by.rusak.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserRegistrationConverter extends UserBaseConverter<UserRegistrationRequest, User>{

    private final PasswordEncoder passwordEncoder;
    @Override
    public User convert(UserRegistrationRequest source) {

        User user = new User();

        user.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                source.getUserLogin(),
                passwordEncoder.encode(source.getUserPassword())
                //source.getUserPassword()
        );

        user.setCredentials(credentials);

        return doConvert(user, source);
    }


}

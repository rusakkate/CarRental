package by.rusak.controller.converters;

import by.rusak.controller.requests.UserCreateRequest;
import by.rusak.domain.Credentials;
import by.rusak.domain.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, User> {

    @Override
    public User convert(UserCreateRequest source) {

        User user = new User();

        user.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)
        );

        user.setCredentials(credentials);

        return doConvert(user, source);
    }
}

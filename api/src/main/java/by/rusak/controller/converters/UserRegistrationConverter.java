package by.rusak.controller.converters;

import by.rusak.controller.requests.UserRegistrationRequest;
import by.rusak.domain.hibernate.Credentials;
import by.rusak.domain.hibernate.HibernateUser;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class UserRegistrationConverter extends UserBaseConverter<UserRegistrationRequest, HibernateUser>{

    @Override
    public HibernateUser convert(UserRegistrationRequest source) {

        HibernateUser hibernateUser = new HibernateUser();

        hibernateUser.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                source.getUserLogin(),
                source.getUserPassword()
        );

        hibernateUser.setCredentials(credentials);

        return doConvert(hibernateUser, source);
    }


}

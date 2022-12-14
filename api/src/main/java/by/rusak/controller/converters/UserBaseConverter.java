package by.rusak.controller.converters;

import by.rusak.controller.requests.UserCreateRequest;
import by.rusak.domain.User;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public User doConvert(User userForUpdate, UserCreateRequest request) {

        userForUpdate.setSurname(request.getSurname());
        userForUpdate.setUserName(request.getUserName());
        userForUpdate.setBirth(request.getBirth());
        userForUpdate.setDriverLicenseNumber(request.getDriverLicenseNumber());
        userForUpdate.setDriverLicenseDate(request.getDriverLicenseDate());
        userForUpdate.setEmail(request.getEmail());

        /*System fields filling*/
        userForUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        userForUpdate.setIsDeleted(false);
        userForUpdate.setIsEnabled(false);

        return userForUpdate;
    }

}
package by.rusak.repository.jdbctemplate;

import by.rusak.domain.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.rusak.repository.columns.UserTableColumns.BIRTH_DATE;
import static by.rusak.repository.columns.UserTableColumns.CHANGED;
import static by.rusak.repository.columns.UserTableColumns.CREATED;
import static by.rusak.repository.columns.UserTableColumns.DRIVER_LICENSE_DATE;
import static by.rusak.repository.columns.UserTableColumns.DRIVER_LICENSE_NUMBER;
import static by.rusak.repository.columns.UserTableColumns.EMAIL;
import static by.rusak.repository.columns.UserTableColumns.ID;
import static by.rusak.repository.columns.UserTableColumns.IS_DELETED;
import static by.rusak.repository.columns.UserTableColumns.LATITUDE;
import static by.rusak.repository.columns.UserTableColumns.LOGIN;
import static by.rusak.repository.columns.UserTableColumns.LONGITUDE;
import static by.rusak.repository.columns.UserTableColumns.NAME;
import static by.rusak.repository.columns.UserTableColumns.PASSWORD;
import static by.rusak.repository.columns.UserTableColumns.SURNAME;

@Component
public class UserRowMapper implements RowMapper<User> {

    private static final Logger log = Logger.getLogger(UserRowMapper.class);

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        log.info("User row mapping start");

        User user = new User();

        user.setId(rs.getLong(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setBirth(rs.getTimestamp(BIRTH_DATE));
        user.setCreationDate(rs.getTimestamp(CREATED));
        user.setModificationDate(rs.getTimestamp(CHANGED));
        user.setIsDeleted(rs.getBoolean(IS_DELETED));
        user.setLogin(rs.getString(LOGIN));
        user.setPassword(rs.getString(PASSWORD));
        user.setDriverLicenseNumber(rs.getString(DRIVER_LICENSE_NUMBER));
        user.setDriverLicenseDate(rs.getTimestamp(DRIVER_LICENSE_DATE));
        user.setEmail(rs.getString(EMAIL));
        user.setLatitude(rs.getLong(LATITUDE));
        user.setLongitude(rs.getLong(LONGITUDE));

        log.info("User row mapping end");
        return user;
    }
}

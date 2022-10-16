package by.rusak.repository.jdbctemplate;


import by.rusak.domain.User;
import by.rusak.repository.user.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateUserRepository implements UserRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserRowMapper userRowMapper;

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select * from carrental.users where id_user = " + id, userRowMapper);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from carrental.users limit " + limit + " offset " + offset, userRowMapper);
    }

    @Override
    public User create(User object) {
        final String insertQuery =
                "insert into carrental.users (surname, user_name, birthday, driver_license_number, driver_license_date, user_login, \n" +
                        "user_password, email, latitude, longitude,  creation_date, modification_date, is_deleted) " +
                        " values (:surname, :userName, :birth, :driverLicenseNumber, :driverLicenseDate, :login, " +
                        ":password, :email, :latitude, :longitude, :creationDate, :modificationDate, :isDeleted);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("surname", object.getSurname());
        mapSqlParameterSource.addValue("userName", object.getUserName());
        mapSqlParameterSource.addValue("birth", object.getBirth());
        mapSqlParameterSource.addValue("driverLicenseNumber", object.getDriverLicenseNumber());
        mapSqlParameterSource.addValue("driverLicenseDate", object.getDriverLicenseDate());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());
        mapSqlParameterSource.addValue("email", object.getEmail());
        mapSqlParameterSource.addValue("latitude", object.getLatitude());
        mapSqlParameterSource.addValue("longitude", object.getLongitude());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('carrental.users_id_user_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from carrental.users where id_user = " + id);
        return id;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public Map<String, Object> getUserStats() {
       /* return jdbcTemplate.query("select carrental.get_users_stats_average_weight(true)", resultSet -> {

            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });*/
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {

        final String searchByLoginQuery = "select * from carrental.users where user_login = :login";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("login", login);

        return Optional.of(namedParameterJdbcTemplate.queryForObject(searchByLoginQuery, mapSqlParameterSource, userRowMapper));
    }
}
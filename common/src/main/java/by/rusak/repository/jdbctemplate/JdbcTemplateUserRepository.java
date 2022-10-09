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
        //return jdbcTemplate.queryForObject("select * from carrental.users where id = " + id, userRowMapper);
        return null;
    }

    @Override
    public Optional<User> findOne(Long id) {

        //return Optional.of(findById(id));
        return null;
    }

    @Override
    public List<User> findAll() {

        //return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
        return null;
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        //return jdbcTemplate.query("select * from carrental.users limit " + limit + " offset " + offset, userRowMapper);
        return null;
    }

    @Override
    public User create(User object) {
/*        final String insertQuery =
                "insert into carrental.users (user_name, surname, birth, is_deleted, creation_date, modification_date, weight, user_login, user_password) " +
                        " values (:userName, :surname, :birth, :isDeleted, :creationDate, :modificationDate, :weight, :login, :password);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userName", object.getUserName());
        mapSqlParameterSource.addValue("surname", object.getSurname());
        mapSqlParameterSource.addValue("birth", object.getBirth());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("weight", object.getWeight());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('carrental.users_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);*/
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
       /* jdbcTemplate.update("delete from carrental.users where id = " + id);
        return id;*/
        return null;
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
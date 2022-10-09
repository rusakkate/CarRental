package by.rusak.repository.jdbctemplate;


import by.rusak.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateRoleRepository implements RoleRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RoleRowMapper rowMapper;

    @Override
    public Role findById(Long id) {
        //return jdbcTemplate.queryForObject("select * from carrental.roles where id = " + id, rowMapper);
        return null;
    }

    @Override
    public Optional<Role> findOne(Long id) {

        //return Optional.of(findById(id));
        return null;
    }

    @Override
    public List<Role> findAll() {

        //return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
        return null;
    }

    @Override
    public List<Role> findAll(int limit, int offset) {
        //return jdbcTemplate.query("select * from carrental.roles limit " + limit + " offset " + offset, rowMapper);
        return null;
    }

    @Override
    public Role create(Role object) {
        /*final String insertQuery =
                "insert into carrental.roles (role_name, creation_date, modification_date) " +
                        " values (:roleName, :creationDate, :modificationDate);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("roleName", object.getRoleName());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('carrental.roles_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);*/
        return null;
    }

    @Override
    public Role update(Role object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        /*jdbcTemplate.update("delete from carrental.roles where id = " + id);
        return id;*/
        return null;
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return jdbcTemplate.query(
                "select * from carrental.roles " +
                        " inner join carrental.user_role lru on roles.id_role = lru.id_role " +
                        " where lru.id_user = " + userId,
                rowMapper);
    }
}
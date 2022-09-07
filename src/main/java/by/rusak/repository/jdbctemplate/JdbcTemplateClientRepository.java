package by.rusak.repository.jdbctemplate;

import by.rusak.domain.Client;
import by.rusak.repository.client.ClientRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateClientRepository implements ClientRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final ClientRowMapper clientRowMapper;

    @Override
    public List <Client> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List <Client>  findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from carrental.clients limit " + limit + " offset " + offset, clientRowMapper);
    }

    @Override
    public Client findById(Long id) {
        return jdbcTemplate.queryForObject("select * from carrental.clients where id_client = " + id, clientRowMapper);
    }
    public Optional<Client> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public Client update(Client object) {
        final String insertQuery =
                "update carrental.clients " +
                        "set " +
                        "surname = :surname, client_name = :name, birthday = :birth, " +
                        "driver_license_number = :licenseNumber, driver_license_date = :licenseDate," +
                        " client_login = :login, client_password = :password, email = :email," +
                        " latitude = :latitude, longitude = :longitude," +
                        "creation_date = :creationDate, modification_date = :modificationDate, is_deleted = :isDeleted" +
                        " where id_client = :id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("surname", object.getSurname());
        mapSqlParameterSource.addValue("name", object.getName());
        mapSqlParameterSource.addValue("birth", object.getBirthday());
        mapSqlParameterSource.addValue("licenseNumber", object.getLicenseNumber());
        mapSqlParameterSource.addValue("licenseDate", object.getLicenseDate());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());
        mapSqlParameterSource.addValue("email", object.getEmail());
        mapSqlParameterSource.addValue("latitude", object.getLatitude());
        mapSqlParameterSource.addValue("longitude", object.getLongitude());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("id", object.getId());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from carrental.clients where id_client = " + id);
        return id;
    }

    @Override
    public Client create(Client object) {
        final String insertQuery =
                "insert into carrental.clients (surname, client_name, birthday, driver_license_number, driver_license_date, " +
                        "client_login, client_password, email, latitude, longitude, creation_date, modification_date, is_deleted) " +
                        " values (:surname, :name, :birth, :licenseNumber, :licenseDate, " +
                        ":login, :password, :email, :latitude, :longitude, :creationDate, :modificationDate, :isDeleted);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("surname", object.getSurname());
        mapSqlParameterSource.addValue("name", object.getName());
        mapSqlParameterSource.addValue("birth", object.getBirthday());
        mapSqlParameterSource.addValue("licenseNumber", object.getLicenseNumber());
        mapSqlParameterSource.addValue("licenseDate", object.getLicenseDate());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());
        mapSqlParameterSource.addValue("email", object.getEmail());
        mapSqlParameterSource.addValue("latitude", object.getLatitude());
        mapSqlParameterSource.addValue("longitude", object.getLongitude());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('carrental.client_id_client_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }


    @Override
    public List<Client> findByName(String string) {
        return jdbcTemplate.query("select * from carrental.clients where surname like '%" + string +
                "%' or client_name like '%" + string + "%'", clientRowMapper);
    }

    @Override
    public List<Client> findRiskClient() {
        return jdbcTemplate.query("select * " +
                "from carrental.clients " +
                "where (select extract (year from (select age(birthday)) ) < 27 ) " +
                "   OR (select extract (year from (select age(birthday)) ) > 60 ) " +
                "   OR (select extract (year from (select age(driver_license_date)) ) < 2 )", clientRowMapper);
    }

    @Override
    public Map<String, Object> getAvgClientsAge() {
        return jdbcTemplate.query("select get_clients_average_age(false)", resultSet -> {

            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });
    }

}

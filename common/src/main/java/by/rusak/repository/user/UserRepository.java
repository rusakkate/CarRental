package by.rusak.repository.user;

import by.rusak.domain.User;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

@Repository
//@Primary
@RequiredArgsConstructor
public class UserRepository implements UserRepositoryInterface {

    private static final Logger log = Logger.getLogger(UserRepository.class);

    @Override
    public User findById(Long id) {
        final String findByIdQuery = "select * from carrental.users where id_user = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return userRowMapping(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404, UUIDGenerator.generateUUID());
            }
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("DB connection process issues");
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    private Connection getConnection() throws SQLException {
        try {
            String driver = "databaseProperties.getDriverName()";

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("JDBC Driver Cannot be loaded!", e);
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = "databaseProperties.getUrl()";
        String login = "databaseProperties.getLogin()";
        String password = "databaseProperties.getPassword()";

        return DriverManager.getConnection(url, login, password);
    }

    private User userRowMapping(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong(ID));
        user.setSurname(rs.getString(SURNAME));
        user.setUserName(rs.getString(NAME));
        user.setBirth(rs.getTimestamp(BIRTH_DATE));
        user.setDriverLicenseNumber(rs.getString(DRIVER_LICENSE_NUMBER));
        user.setDriverLicenseDate(rs.getTimestamp(DRIVER_LICENSE_DATE));
        user.setLogin(rs.getString(LOGIN));
        user.setPassword(rs.getString(PASSWORD));
        user.setEmail(rs.getString(EMAIL));
        user.setLatitude(rs.getLong(LATITUDE));
        user.setLongitude(rs.getLong(LONGITUDE));
        user.setCreationDate(rs.getTimestamp(CREATED));
        user.setModificationDate(rs.getTimestamp(CHANGED));
        user.setIsDeleted(rs.getBoolean(IS_DELETED));

        return user;

/*        return User.builder()
                .id(rs.getLong(ID))
                .userName(rs.getString(NAME))
                .surname(rs.getString(SURNAME))
                .birth(rs.getTimestamp(BIRTH_DATE))
                .creationDate(rs.getTimestamp(CREATED))
                .modificationDate(rs.getTimestamp(CHANGED))
                .weight(rs.getDouble(WEIGHT))
                .build();*/
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        final String findAllQuery = "select * from carrental.users order by id_user limit " + limit + " offset " + offset;

        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                result.add(userRowMapping(rs));
            }

            return result;
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User create(User object) {
        final String insertQuery =
                "insert into carrental.users (surname, user_name, birthday, creation_date, modification_date, is_deleted) " +
                        " values (?, ?, ?, ?, ?, ?);";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setString(1, object.getSurname());
            statement.setString(2, object.getUserName());
            statement.setTimestamp(3, object.getBirth());
            statement.setTimestamp(4, object.getCreationDate());
            statement.setTimestamp(5, object.getModificationDate());
            statement.setBoolean(6, object.getIsDeleted());

            //executeUpdate - for INSERT, UPDATE, DELETE
            statement.executeUpdate();
            //For select
            //statement.executeQuery();

            /*Get users last insert id for finding new object in DB*/
            ResultSet resultSet = connection.prepareStatement("SELECT currval('carrental.users_id_user_seq') as last_id").executeQuery();
            resultSet.next();
            long userLastInsertId = resultSet.getLong("last_id");

            return findById(userLastInsertId);
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User update(User object) {
        final String updateQuery =
                "update carrental.users " +
                        "set " +
                        "surname = ?, user_name = ?,  birth = ?, creation_date = ?, modification_date = ?, is_deleted = ?" +
                        " where id_user = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, object.getSurname());
            statement.setString(2, object.getUserName());
            statement.setTimestamp(3, object.getBirth());
            statement.setTimestamp(4, object.getCreationDate());
            statement.setTimestamp(5, object.getModificationDate());
            statement.setBoolean(6, object.getIsDeleted());
            statement.setLong(7, object.getId());

            statement.executeUpdate();

            return findById(object.getId());
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Long delete(Long id) {
        final String deleteQuery =
                "delete from carrental.users where id_user = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();

            return id;
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Map<String, Object> getUserStats() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }
}


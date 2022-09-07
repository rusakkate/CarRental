package by.rusak.repository.jdbctemplate;

import by.rusak.domain.Car;
import by.rusak.repository.car.CarRepositoryInterface;
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
public class JdbcTemplateCarRepository implements CarRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarRowMapper carRowMapper;

    @Override
    public List<Car> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Car> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from carrental.cars limit " + limit + " offset " + offset, carRowMapper);
    }

    @Override
    public Car create(Car object) {
        final String insertQuery =
                "insert into carrental.cars (idType, plateNumber, productionYear, rating, " +
                        "photo, creation_date, modification_date, is_deleted) " +
                        " values (:idType, :plateNumber, :productionYear, :rating, :photo, " +
                        ":priceDay, :creationDate, :modificationDate, :isDeleted);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idType", object.getIdType());
        mapSqlParameterSource.addValue("plateNumber", object.getPlateNumber());
        mapSqlParameterSource.addValue("productionYear", object.getProductionYear());
        mapSqlParameterSource.addValue("rating", object.getRating());
        mapSqlParameterSource.addValue("priceDay", object.getPriceDay());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('carrental.car_id_car_seq) as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public Car findById(Long id) {
        return jdbcTemplate.queryForObject("select * from carrental.cars where id_car = " + id, carRowMapper);
    }

    @Override
    public Car update(Car object) {
        final String insertQuery =
                "update carrental.cars " +
                        "set " +
                        "idType = :idType, plateNumber = :plateNumber, " +
                        "productionYear = :productionYear, rating = :rating," +
                        " photo = :photo, priceDay = :priceDay, creationDate = :creationDate," +
                        " modificationDate = :modificationDate, isDeleted = :isDeleted";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idType", object.getIdType());
        mapSqlParameterSource.addValue("plateNumber", object.getPlateNumber());
        mapSqlParameterSource.addValue("productionYear", object.getProductionYear());
        mapSqlParameterSource.addValue("rating", object.getRating());
        mapSqlParameterSource.addValue("priceDay", object.getPriceDay());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("id", object.getId());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from carrental.cars where id_car = " + id);
        return id;
    }

    @Override
    public List<Car> findTheHighestRatingCar() {
        return jdbcTemplate.query("select * from carrental.cars where rating = (select max(rating) from carrental.cars)", carRowMapper);
    }

    @Override
    public Map<String, Object> getAvgCarRating() {
        return jdbcTemplate.query("select get_average_cars_rating(false)", resultSet -> {

            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });
    }

    @Override
    public List<Car> findCarRatingBelowAvg() {
        return jdbcTemplate.query("select * from carrental.cars where rating < (select avg(rating) " +
                "from carrental.cars " +
                "where is_deleted = false); ", carRowMapper);
    }

    @Override
    public List<Car> findTheOldestCar() {
        return jdbcTemplate.query("select * from carrental.cars where production_year = (select min(production_year) " +
                "from carrental.cars " +
                "where is_deleted = false); ", carRowMapper);
    }

    @Override
    public List<Car> findTheCheapestCar() {
        return jdbcTemplate.query("select * from carrental.cars where price_day = (select min(price_day) " +
                "from carrental.cars " +
                "where is_deleted = false); ", carRowMapper);
    }

    @Override
    public Map<String, Object> getAvgPriceDay() {
        return jdbcTemplate.query("select get_average_cars_rating(false)", resultSet -> {

            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });
    }

    @Override
    public List<Car> findCarPriceDayBelowAvg() {
        return jdbcTemplate.query("select * from carrental.cars where price_day < (select avg(price_day) " +
                "from carrental.cars " +
                "where is_deleted = false); ", carRowMapper);
    }
}

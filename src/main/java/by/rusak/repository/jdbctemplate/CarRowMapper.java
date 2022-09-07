package by.rusak.repository.jdbctemplate;

import by.rusak.domain.Car;
import by.rusak.domain.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.rusak.repository.car.CarTableColumns.ID;
import static by.rusak.repository.car.CarTableColumns.ID_TYPE;
import static by.rusak.repository.car.CarTableColumns.PLATE_NUMBER;
import static by.rusak.repository.car.CarTableColumns.PRODUCTION_YEAR;
import static by.rusak.repository.car.CarTableColumns.RATING;
import static by.rusak.repository.car.CarTableColumns.PRICE_DAY;
import static by.rusak.repository.car.CarTableColumns.CREATED;
import static by.rusak.repository.car.CarTableColumns.CHANGED;
import static by.rusak.repository.car.CarTableColumns.IS_DELETED;


@Component
public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rs, int i) throws SQLException {
        Car car = new Car();

        car.setId(rs.getLong(ID));
        car.setIdType(rs.getInt(ID_TYPE));
        car.setPlateNumber(rs.getString(PLATE_NUMBER));
        car.setProductionYear(rs.getInt(PRODUCTION_YEAR));
        car.setRating(rs.getLong(RATING));
        car.setPriceDay(rs.getLong(PRICE_DAY));
        car.setCreationDate(rs.getTimestamp(CREATED));
        car.setModificationDate(rs.getTimestamp(CHANGED));
        car.setIsDeleted(rs.getBoolean(IS_DELETED));

        return car;
    }
}

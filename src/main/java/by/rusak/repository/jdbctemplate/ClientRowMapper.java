package by.rusak.repository.jdbctemplate;

import by.rusak.domain.Client;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.rusak.repository.client.ClientTableColumns.BIRTH_DATE;
import static by.rusak.repository.client.ClientTableColumns.CHANGED;
import static by.rusak.repository.client.ClientTableColumns.CREATED;
import static by.rusak.repository.client.ClientTableColumns.EMAIL;
import static by.rusak.repository.client.ClientTableColumns.ID;
import static by.rusak.repository.client.ClientTableColumns.IS_DELETED;
import static by.rusak.repository.client.ClientTableColumns.LATITUDE;
import static by.rusak.repository.client.ClientTableColumns.LICENSE_DATE;
import static by.rusak.repository.client.ClientTableColumns.LICENSE_NUMBER;
import static by.rusak.repository.client.ClientTableColumns.LOGIN;
import static by.rusak.repository.client.ClientTableColumns.LONGITUDE;
import static by.rusak.repository.client.ClientTableColumns.NAME;
import static by.rusak.repository.client.ClientTableColumns.PASSWORD;
import static by.rusak.repository.client.ClientTableColumns.SURNAME;

@Component
public class ClientRowMapper implements RowMapper<Client> {

    //private static final Logger log = Logger.getLogger(ClientRowMapper.class);

    @Override
    public Client mapRow(ResultSet rs, int i) throws SQLException {
        //log.info("Client row mapping start");
        Client client = new Client();

        client.setId(rs.getLong(ID));
        client.setSurname(rs.getString(SURNAME));
        client.setName(rs.getString(NAME));
        client.setBirthday(rs.getTimestamp(BIRTH_DATE));
        client.setLicenseNumber(rs.getString(LICENSE_NUMBER));
        client.setLicenseDate(rs.getTimestamp(LICENSE_DATE));
        client.setLogin(rs.getString(LOGIN));
        client.setPassword(rs.getString(PASSWORD));
        client.setEmail(rs.getString(EMAIL));
        client.setLatitude((long) rs.getDouble(LATITUDE));
        client.setLongitude((long) rs.getDouble(LONGITUDE));
        client.setCreationDate(rs.getTimestamp(CREATED));
        client.setModificationDate(rs.getTimestamp(CHANGED));
        client.setIsDeleted(rs.getBoolean(IS_DELETED));

        //log.info("Client row mapping end");
        return client;
    }
}

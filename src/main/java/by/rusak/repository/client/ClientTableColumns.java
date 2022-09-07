package by.rusak.repository.client;

import java.sql.Timestamp;

public interface ClientTableColumns {
    String ID = "id_client";
    String SURNAME = "surname";
    String NAME = "client_name";
    String BIRTH_DATE = "birthday";
    String LICENSE_NUMBER = "driver_license_number";
    String LICENSE_DATE = "driver_license_date";
    String LOGIN = "client_login";
    String PASSWORD = "client_password";
    String EMAIL = "email";
    String LATITUDE = "latitude";
    String LONGITUDE = "longitude";
    String CREATED = "creation_date";
    String CHANGED = "modification_date";
    String IS_DELETED = "is_deleted";

}

package by.rusak.controller.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCreateRequest {

    private String userName;

    private String surname;

    private Timestamp birth;

    private String driverLicenseNumber;

    private Timestamp driverLicenseDate;


}

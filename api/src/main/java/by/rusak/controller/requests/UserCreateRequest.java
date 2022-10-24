package by.rusak.controller.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Validated
@ApiModel(description = "Create user object without system info")
public class UserCreateRequest {

    @ApiModelProperty(required = true, allowableValues = "kate", dataType = "string", notes = "user's name")
    @NotNull
    @Size(min = 2, max = 20)
    private String userName;

    @ApiModelProperty(required = true, allowableValues = "rusak", dataType = "string", notes = "user's surname")
    @NotNull
    @Size(min = 2, max = 50)
    private String surname;

    @ApiModelProperty(required = true, allowableValues = "1665685166000", dataType = "timestamp", notes = "user's birth")
    @Past
    private Timestamp birth;

    @ApiModelProperty(required = true, allowableValues = "458796hh", dataType = "string", notes = "user's driverLicenseNumber")
    private String driverLicenseNumber;

    @ApiModelProperty(required = true, allowableValues = "1665685166000", dataType = "timestamp", notes = "user's driverLicenseDate")
    @Past
    private Timestamp driverLicenseDate;

    @ApiModelProperty(required = true, allowableValues = "myemail@mail.ru", dataType = "string", notes = "user's email")
    @Email
    private String email;

}

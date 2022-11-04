package by.rusak.controller.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Past;
import java.security.Principal;
import java.sql.Timestamp;

@Data
@Validated
@ApiModel(description = "Change user information about driver license")
public class UserChangeDriverLicenseRequest {

    @ApiModelProperty(required = true, allowableValues = "458796hh", dataType = "string", notes = "user's driverLicenseNumber")
    private String driverLicenseNumber;

    @ApiModelProperty(required = true, allowableValues = "1665685166000", dataType = "timestamp", notes = "user's driverLicenseDate")
    @Past
    private Timestamp driverLicenseDate;

}


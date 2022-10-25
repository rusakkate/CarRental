package by.rusak.controller.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Validated
@ApiModel(description = "Registration user object without system info")
public class UserRegistrationRequest extends UserCreateRequest {

    @ApiModelProperty(required = true, allowableValues = "rusakkate", dataType = "string", notes = "user's login")
    @NotNull
    @Size(min = 2, max = 100)
    private String userLogin;

    @ApiModelProperty(required = true, allowableValues = "12345", dataType = "string", notes = "user's password")
    @NotNull
    @Size(min = 2, max = 200)
    private String userPassword;


}

package by.rusak.controller.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(description = "Login request")
@Validated
public class AuthRequest {

    @NotNull
    @Size(min = 2, max = 100)
    @ApiModelProperty(required = true, allowableValues = "rusakkate", dataType = "string", notes = "user's login")
    private String login;

    @NotNull
    @Size(min = 2, max = 200)
    @ApiModelProperty(required = true, allowableValues = "12345", dataType = "string", notes = "user's password")
    private String password;
}
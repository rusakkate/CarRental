package by.rusak.controller.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Validated
@ApiModel(description = "Change user password")
public class UserChangePasswordRequest {

    @ApiModelProperty(required = true, allowableValues = "12345", dataType = "string", notes = "user's password")
    @NotNull
    @Size(min = 2, max = 200)
    private String userPassword;
}

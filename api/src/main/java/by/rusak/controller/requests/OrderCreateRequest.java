package by.rusak.controller.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Validated
@ApiModel(description = "Create order")
public class OrderCreateRequest {

    @ApiModelProperty(required = true, dataType = "long", notes = "car's id")
    @NotNull
    private Long idCar;

    @ApiModelProperty(required = true, dataType = "timestamp", notes = "rental start date")
    @FutureOrPresent
    @NotNull
    private Timestamp rentalStartDate;

    @ApiModelProperty(required = true, dataType = "timestamp", notes = "rental end date")
    @Future
    @NotNull
    private Timestamp rentalEndDate;

}

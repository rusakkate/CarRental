package by.rusak.controller;

import by.rusak.domain.Car;
import by.rusak.domain.Schedule;
import by.rusak.service.ScheduleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService service;

    @ApiOperation(value = "Finding free car's schedule car by car's id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/carfreeschedule/{idCar}")
    public ResponseEntity<Object> findFreeScheduleCarById1(@PathVariable Long idCar, @ApiIgnore Pageable pageable) {
        Timestamp rentalStartDay = new Timestamp(new Date().getTime());
        Page<Schedule> schedules = service.findFreeSchedulesByIdCar(idCar, rentalStartDay, pageable);
        Map<String, Object> result = new HashMap<>();
        if (schedules.isEmpty()) {
            result.put("message", "Car with id " + idCar + " does not exist");
        } else {
            result.put("message", schedules);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @ApiOperation(value = "Finding free schedule for period, period in format '2022-11-10 00:00:00.279000'")
    @GetMapping(value = "/freeschedule/{rentalStartDay}/{rentalEndDate}}")
    public ResponseEntity<Object> findFreeScheduleForPeriod(@PathVariable Timestamp rentalStartDay,
                                                            @PathVariable Timestamp rentalEndDate, @ApiIgnore Pageable pageable) {
        Page<Schedule> schedules = service.findFreeSchedulesByPeriod(rentalStartDay, rentalEndDate, pageable);
        if (rentalEndDate.before(rentalStartDay)) {
            return new ResponseEntity<>("Rental end date can't be before rental start day, try again", HttpStatus.BAD_REQUEST);
        } else if (schedules.isEmpty()) {
            return new ResponseEntity<>("There are not free cars for the period", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("Free schedule ", schedules), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Finding free cars for period, period in format '2022-11-10 00:00:00.279000'")
    @GetMapping(value = "/freecarforperiod/{rentalStartDay}/{rentalEndDate}")
    public ResponseEntity<Object> findFreeCarForPeriod(@PathVariable Timestamp rentalStartDay, @PathVariable Timestamp rentalEndDate) {
        List<Integer> schedules = service.findFreeCarsForPeriod(rentalStartDay, rentalEndDate);
        if (rentalEndDate.before(rentalStartDay)) {
            return new ResponseEntity<>("Rental end date can't be before rental start day, try again", HttpStatus.BAD_REQUEST);
        } else if (schedules.isEmpty()) {
            return new ResponseEntity<>("There are not free cars for the period", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("Free cars for the period ", schedules), HttpStatus.OK);
        }

    }


}
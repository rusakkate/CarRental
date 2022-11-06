package by.rusak.controller;

import by.rusak.domain.Car;
import by.rusak.service.CarService;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService service;

    @ApiOperation(value = "Finding all cars with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping
    public ResponseEntity<Page<Car>> findAllCars(@ApiIgnore Pageable pageable) {
        Page<Car> cars = service.findAll(pageable);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding car by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findCarById(@PathVariable Long id) {
        Car car = service.findById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all cars by brand with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/brand/{brand}")
    public ResponseEntity<Object> findCarsByBrand(@PathVariable String brand, @ApiIgnore Pageable pageable) {
        Page<Car> cars = service.findCarsByBrand(brand, pageable);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all cars by brand and model with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/type/{brand}/{model}")
    public ResponseEntity<Object> findCarsByModel(@PathVariable String brand, @PathVariable String model, @ApiIgnore Pageable pageable) {
        Page<Car> cars = service.findCarsByBrandAndModel(brand, model, pageable);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all cars by product year with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/productyear/{productyear}")
    public ResponseEntity<Object> findCarsByProdYear(@PathVariable Integer productyear, @ApiIgnore Pageable pageable) {
        Page<Car> cars = service.findCarByProductionYear(productyear, pageable);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all cars by day price before with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/pricebefore/{price}")
    public ResponseEntity<Object> findCarsByPriceBefore(@PathVariable Double price, @ApiIgnore Pageable pageable) {
        Page<Car> cars = service.findCarsByPriceDayBefore(price, pageable);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}

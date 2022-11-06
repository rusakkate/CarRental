package by.rusak.controller;


import by.rusak.controller.generetor.PdfGenerator;
import by.rusak.domain.Order;
import by.rusak.service.OrderService;
import com.lowagie.text.DocumentException;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/ord")
public class AdminOrderController {
    private final OrderService service;

    @ApiOperation(value = "Finding all orders with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token",
                    required = true, paramType = "header", dataType = "string"),
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
    public ResponseEntity<Page<Order>> findAllOrd(@ApiIgnore Pageable pageable) {
        Page<Order> ords = service.findAll(pageable);
        return new ResponseEntity<>(ords, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding order by order id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true,
                    paramType = "header", dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findOrdById(@PathVariable Long id) {
        Order ord = service.findOrderById(id);
        Map<String, Order> result = Collections.singletonMap("result", ord);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding orders by brand")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true,
                    paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/ord/{brand}")
    public ResponseEntity<Object> findOrdByBrand(@PathVariable String brand) {
        List<Object[]> ords = service.findByBrand(brand);
        return new ResponseEntity<>(ords, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user's orders by user id  with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true,
                    paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/orduser/{id}")
    public ResponseEntity<Object> findOrdByUserId(@PathVariable Long id, @ApiIgnore Pageable pageable) {
        Page<Order> ords = service.findOrdersByIdUser(id, pageable);
        return new ResponseEntity<>(ords, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding orders by car id with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true,
                    paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue="0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue="10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/ordcar/{id}")
    public ResponseEntity<Object> findOrdByCarId(@PathVariable Long id, @ApiIgnore Pageable pageable) {
        Page<Order> ords = service.findOrdersByIdCar(id, pageable);
        return new ResponseEntity<>(ords, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all orders in pdf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token",
                    required = true, paramType = "header", dataType = "string")
    })
    @GetMapping("/export-to-pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());

        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=ordList" + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        List<Order> listofOrds = service.findAll();

        PdfGenerator generator = new PdfGenerator();
        generator.generateListOrd(listofOrds, response);
    }
}

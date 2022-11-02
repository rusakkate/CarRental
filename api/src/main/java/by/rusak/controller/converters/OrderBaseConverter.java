package by.rusak.controller.converters;


import by.rusak.controller.requests.OrderCreateRequest;
import by.rusak.domain.Order;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;


public abstract class OrderBaseConverter <S, T> implements Converter<S, T>  {
    public Order doConvert(Order orderForUpdate, OrderCreateRequest request) {

        orderForUpdate.setIdUser(request.getIdUser());
        orderForUpdate.setIdCar(request.getIdCar());
        orderForUpdate.setRentalStartDate(request.getRentalStartDate());
        orderForUpdate.setRentalEndDate(request.getRentalEndDate());
        orderForUpdate.setModificationDate(new Timestamp(new Date().getTime()));

        return orderForUpdate;
    }

}

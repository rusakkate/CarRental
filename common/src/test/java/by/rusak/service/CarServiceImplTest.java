package by.rusak.service;

import by.rusak.domain.Car;
import by.rusak.repository.CarRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    public void findCarsByBrandTest_ShouldReturnCar(){
       /* String brand = "toyota";
        List<Car> cars = createCars();

        Mockito.when(carRepository.findCarsByBrand(any().toString())).thenReturn(cars);

        List<Car> result = carService.findCarsByBrand(brand);

        //Assertions.assertNotNull(result);
        //Assertions.assertEquals(1, result.size());
        Assertions.assertEquals (cars.get(0), result.get(0));

      /*  List<Car> cars = createCars();

        Mockito.when(carRepository.findAll()).thenReturn(cars);

        List<Car> result;
        result = carService.findAll();

        Assertions.assertEquals(2, result.size());*/

    }


    private List<Car> createCars() {
        Car firstCar = new Car();
        Car secondCar = new Car();

        firstCar.setId(1L);
        firstCar.setBrand("toyota");
        firstCar.setModel("yaris");
        firstCar.setPlateNumber("8635 PA-7");
        firstCar.setProductionYear(2022);
        firstCar.setPriceDay(50.0);
        firstCar.setDeleted(false);
        firstCar.setCreationDate(new Timestamp(new Date().getTime()));
        firstCar.setModificationDate(new Timestamp(new Date().getTime()));

        secondCar.setId(2L);
        secondCar.setBrand("audi");
        secondCar.setModel("xc5");
        secondCar.setPlateNumber("8635 PA-7");
        secondCar.setProductionYear(2022);
        secondCar.setPriceDay(50.0);
        secondCar.setDeleted(false);
        secondCar.setCreationDate(new Timestamp(new Date().getTime()));
        secondCar.setModificationDate(new Timestamp(new Date().getTime()));

        return List.of(firstCar, secondCar);
    }

}

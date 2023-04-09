package by.rusak.service;

import by.rusak.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleService {

    Page<Schedule> findFreeSchedulesByIdCar(Long idCar, Timestamp rentalStartDay, Pageable pageable);

    Page<Schedule> findFreeSchedulesByPeriod(Timestamp rentalStartDay, Timestamp rentalEndDate, Pageable pageable);

    int changeIsFreeToBusy (Long idCar, Timestamp rentalStartDate, Timestamp rentalEndDate);

    boolean checkFreePeriod (Long idCar,Timestamp rentalStartDate, Timestamp rentalEndDate);

    List<Integer> findFreeCarsForPeriod (Timestamp rentalStartDate,Timestamp rentalEndDate);

    Timestamp findMaxUseDay ();

    List<Schedule> findAll();


}

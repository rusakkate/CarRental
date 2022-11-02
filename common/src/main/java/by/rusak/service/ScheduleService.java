package by.rusak.service;

import by.rusak.domain.Schedule;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleService {

    List<Schedule> findFreeSchedulesByIdCar(Long idCar, Timestamp rentalStartDay);

    List<Schedule> findSchedulesByUseDayBetweenAndIsFreeIsTrue(Timestamp rentalStartDay, Timestamp rentalEndDate);

    int changeIsFreeToBusy (Long idCar, Timestamp rentalStartDate, Timestamp rentalEndDate);

    boolean checkFreePeriod (Long idCar,Timestamp rentalStartDate, Timestamp rentalEndDate);

    List<Integer> findFreeCarsForPeriod (Timestamp rentalStartDate,Timestamp rentalEndDate);

    Timestamp findMaxUseDay ();


}

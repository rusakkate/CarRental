package by.rusak.service;

import by.rusak.domain.Schedule;
import by.rusak.exception.NoSuchEntityException;
import by.rusak.repository.ScheduleRepository;
import by.rusak.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository repository;

    private final CarService carService;
    @Override
    public Page<Schedule> findFreeSchedulesByIdCar(Long idCar, Timestamp rentalStartDay, Pageable pageable) {

        Page<Schedule> carFreeSchedule = repository.findSchedulesByIdCarAndIsFreeTrueAndUseDayAfter
                (idCar, rentalStartDay, pageable);

        if (carService.findById(idCar).equals(Exception.class)) {
            throw new NoSuchEntityException("Car with id " + idCar
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        } else if (carFreeSchedule.isEmpty()) {
            throw new NoSuchEntityException("Car with id " + idCar
                    + " has not free day", 404, UUIDGenerator.generateUUID());
        } else {
            return carFreeSchedule;
        }
    }

    @Override
    public Page<Schedule> findFreeSchedulesByPeriod(Timestamp rentalStartDay, Timestamp rentalEndDate, Pageable pageable) {
        Page<Schedule> freeSchedule = repository.findSchedulesByUseDayBetweenAndIsFreeIsTrue
                (rentalStartDay, rentalEndDate, pageable);
        if (rentalStartDay.after(rentalEndDate)) {
            throw new IllegalArgumentException("Rental end date can't be before rental start day");
        } else if (freeSchedule.isEmpty()) {
            throw new NoSuchEntityException("There are not free cars for the period",
                    404, UUIDGenerator.generateUUID());
        } else {
            return freeSchedule;
        }
    }

    @Override
    public int changeIsFreeToBusy(Long idCar, Timestamp rentalStartDate, Timestamp rentalEndDate) {
        return repository
                .findByHQLQueryNativeMakeBusyDate
                        (carService.findById(idCar)
                                        .getId(),
                                rentalStartDate,
                                rentalEndDate
                        );
    }

    @Override
    public boolean checkFreePeriod(Long idCar, Timestamp rentalStartDate, Timestamp rentalEndDate) {
        Timestamp maxUseDay = findMaxUseDay();
        if (carService.findById(idCar).equals(Exception.class)) {
            throw new NoSuchEntityException("Car with id " + idCar
                    + " does not exist", 404, UUIDGenerator.generateUUID());
        } else if (rentalStartDate.after(rentalEndDate)) {
            throw new IllegalArgumentException("Rental end date can't be before rental start day");
        } else if (rentalStartDate.after(maxUseDay) || rentalEndDate.after(maxUseDay)) {
            throw new NoSuchEntityException("The schedule for the selected period " +
                    "is not yet available", 404, UUIDGenerator.generateUUID());
        } else {
            return repository
                    .findByHQLQueryNativeCheckFreePeriod
                            (idCar, rentalStartDate, rentalEndDate);
        }
    }

    @Override
    public List<Integer> findFreeCarsForPeriod(Timestamp rentalStartDate, Timestamp rentalEndDate) {
        List<Integer> freeCar =  repository.findByHQLQueryNativeFindFreeCarsForPeriod(rentalStartDate, rentalEndDate);
        if (rentalStartDate.after(rentalEndDate)) {
            throw new IllegalArgumentException("Rental end date can't be before rental start day");
        } else if (freeCar.isEmpty()) {
            throw new NoSuchEntityException("There are not free cars for the period",
                    404, UUIDGenerator.generateUUID());
        } else {
            return freeCar;
        }
    }

    @Override
    public Timestamp findMaxUseDay() {
        return repository.findByHQLQueryNativeMaxUseDay();
    }


}

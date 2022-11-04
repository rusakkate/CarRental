package by.rusak.service;

import by.rusak.domain.Schedule;
import by.rusak.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository repository;

    private final CarService carService;
    @Override
    public Page<Schedule> findFreeSchedulesByIdCar(Long idCar, Timestamp rentalStartDay, Pageable pageable) {
        return repository.findSchedulesByIdCarAndIsFreeTrueAndUseDayAfter
                (carService.findById(idCar).getId(), rentalStartDay, pageable);
    }

    @Override
    public Page<Schedule> findFreeSchedulesByPeriod(Timestamp rentalStartDay, Timestamp rentalEndDate, Pageable pageable) {
        return repository.findSchedulesByUseDayBetweenAndIsFreeIsTrue(rentalStartDay, rentalEndDate, pageable);
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
        return repository
                .findByHQLQueryNativeCheckFreePeriod
                        (carService.findById(idCar)
                                        .getId(),
                                rentalStartDate,
                                rentalEndDate
                        );
    }

    @Override
    public List<Integer> findFreeCarsForPeriod(Timestamp rentalStartDate, Timestamp rentalEndDate) {
        return repository.findByHQLQueryNativeFindFreeCarsForPeriod(rentalStartDate, rentalEndDate);
    }

    @Override
    public Timestamp findMaxUseDay() {
        return repository.findByHQLQueryNativeMaxUseDay();
    }


}

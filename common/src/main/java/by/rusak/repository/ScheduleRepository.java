package by.rusak.repository;

import by.rusak.domain.Schedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>,
        CrudRepository<Schedule, Long>, PagingAndSortingRepository<Schedule, Long> {

    Page<Schedule> findSchedulesByIdCarAndIsFreeTrueAndUseDayAfter(Long idCar, Timestamp rentalStartDay, Pageable pageable);

    Page<Schedule> findSchedulesByUseDayBetweenAndIsFreeIsTrue(Timestamp rentalStartDay, Timestamp rentalEndDate, Pageable pageable);


    @Query(value = "select carrental.change_is_free (:id_car, " +
            ":rental_start_date, :rental_end_date)", nativeQuery = true)
    int findByHQLQueryNativeMakeBusyDate (@Param("id_car") Long idCar,
                                                @Param("rental_start_date") Timestamp rentalStartDate,
                                                @Param("rental_end_date")Timestamp rentalEndDate);

    @Query(value = "select carrental.check_free_period (:id_car, " +
            ":rental_start_date, :rental_end_date)", nativeQuery = true)
    boolean findByHQLQueryNativeCheckFreePeriod (@Param("id_car") Long idCar,
                                                 @Param("rental_start_date") Timestamp rentalStartDate,
                                                 @Param("rental_end_date")Timestamp rentalEndDate);


    @Query(value = "select carrental.find_free_cars_for_period (:rental_start_date, :rental_end_date)",
            nativeQuery = true)
    List<Integer> findByHQLQueryNativeFindFreeCarsForPeriod (@Param("rental_start_date") Timestamp rentalStartDate,
                                                              @Param("rental_end_date")Timestamp rentalEndDate);

    @Query(value ="select max (carrental.cars_schedule.use_day) from carrental.cars_schedule", nativeQuery = true)
    Timestamp findByHQLQueryNativeMaxUseDay ();

}

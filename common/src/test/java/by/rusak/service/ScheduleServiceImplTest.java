package by.rusak.service;

import by.rusak.domain.Schedule;
import by.rusak.repository.ScheduleRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

   @Test
    public void findAllTest_ShouldReturnAllSchedule(){

        List<Schedule> schedules = getSchedule();

        when(this.scheduleRepository.findAll()).thenReturn(schedules);
        List<Schedule> result = scheduleService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals (schedules.get(0), result.get(0));

    }


    private List<Schedule> getSchedule(){
        Schedule firstSchedule = new Schedule();
        Schedule secondSchedule = new Schedule();
        Schedule thirdSchedule = new Schedule();

        firstSchedule.setId(1L);
        firstSchedule.setIdCar(1L);
        firstSchedule.setUseDay(Timestamp.valueOf("2022-11-01 00:00:00.000000"));
        firstSchedule.setIsFree(true);
        firstSchedule.setCreationDate(new Timestamp(new Date().getTime()));
        firstSchedule.setModificationDate(new Timestamp(new Date().getTime()));

        secondSchedule.setId(2L);
        secondSchedule.setIdCar(2L);
        secondSchedule.setUseDay(Timestamp.valueOf("2022-11-01 00:00:00.000000"));
        secondSchedule.setIsFree(true);
        secondSchedule.setCreationDate(new Timestamp(new Date().getTime()));
        secondSchedule.setModificationDate(new Timestamp(new Date().getTime()));

        thirdSchedule.setId(3L);
        thirdSchedule.setIdCar(2L);
        thirdSchedule.setUseDay(Timestamp.valueOf("2022-11-02 00:00:00.000000"));
        thirdSchedule.setIsFree(true);
        thirdSchedule.setCreationDate(new Timestamp(new Date().getTime()));
        thirdSchedule.setModificationDate(new Timestamp(new Date().getTime()));

        return List.of(firstSchedule, secondSchedule, thirdSchedule);
    }
}

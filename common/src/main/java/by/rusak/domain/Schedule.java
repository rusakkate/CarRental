package by.rusak.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "cars_schedule")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car_schedule")
    private Long id;

    @Column(name = "id_car")
    private Long idCar;

    @Column(name = "use_day")
    private Timestamp useDay;

    @Column(name = "is_free")
    private Boolean isFree;

    @Column(name = "creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

}

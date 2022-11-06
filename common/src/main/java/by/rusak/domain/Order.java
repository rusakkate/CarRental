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
@Table(name = "orders")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_car")
    private Long idCar;

    @Column(name = "rental_start_date")
    private Timestamp rentalStartDate;

    @Column(name = "rental_end_date")
    private Timestamp rentalEndDate;

    @Column(name = "order_price")
    private Double orderPrice;

    @Column(name = "creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

}


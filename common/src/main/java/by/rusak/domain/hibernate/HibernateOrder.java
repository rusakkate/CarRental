package by.rusak.domain.hibernate;

import by.rusak.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.security.Timestamp;

@Data
@Entity
@ToString
@Table(name = "orders")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HibernateOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "id_car")
    private Long idCar;

    @Column(name = "rental_start_date")
    private Timestamp rentalStartDate;

    @Column(name = "rental_end_date")
    private Timestamp rentalEndDate;

    @Column(name = "order_price")
    private Double orderPrice;

    @Column(name = "route_distance")
    @JsonIgnore
    private Double routeDistance;

    @Column(name = "route")
    @JsonIgnore
    private String route;

    @Column(name = "creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

/*    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private HibernateUser user;*/

    /*@ManyToOne
    @JoinColumn(name = "id_car")
    @JsonBackReference
    private Car car;*/


}


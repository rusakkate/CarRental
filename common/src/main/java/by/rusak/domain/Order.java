/*
package by.rusak.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "id_car")
    private Long idCar;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "rental_start_date")
    private Timestamp rentalStartDate;

    @Column(name = "rental_end_date")
    private Timestamp rentalEndDate;

    @Column(name = "order_price")
    private Double orderPrice;

    @Column(name = "route_distance")
    private Double routeDistance;

    @Column(name = "route")
    private String route;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_car")
    @JsonBackReference
    private Car car;


}
*/

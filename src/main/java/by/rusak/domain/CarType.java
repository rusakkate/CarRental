package by.rusak.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "car_types")
public class CarType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Long id;

    @Column(name = "id_model")
    private Long idModel;

    @Column(name = "gearbox_type")
    private String gearboxType;

    @Column(name = "number_seats")
    private Integer numberSeats;

    @Column(name = "avr_speed")
    private Double avrSpeed;

    @Column(name = "engine_volume")
    private Double engineVolume;

    @OneToMany(mappedBy = "carType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Car> cars;

    @ManyToOne
    @JoinColumn(name = "id_model")
    @JsonBackReference
    private Model model;
}

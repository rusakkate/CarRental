package by.rusak.domain.hibernate;

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
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class HibernateUser {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String surname;

    @Column (name = "birthday")
    private Timestamp birth;

    /*null, true, false*/
    @Column(name = "is_deleted")
    @JsonIgnore
    private Boolean isDeleted;

    @Column(name = "creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    @JsonIgnore
    private String userPassword;

    @Column(name = "driver_license_number")
    @JsonIgnore
    private String driverLicenseNumber;

    @Column(name = "driver_license_date")
    @JsonIgnore
    private Timestamp driverLicenseDate;

    @Column(name = "email")
    @JsonIgnore
    private String email;

    @Column(name = "latitude")
    @JsonIgnore
    private Long latitude;

    @Column(name = "longitude")
    @JsonIgnore
    private Long longitude;
}
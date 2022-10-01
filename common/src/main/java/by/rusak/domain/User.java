package by.rusak.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.security.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "user_name")
    private String name;

    @Column(name = "birthday")
    private Timestamp birth;

    @Column(name = "driver_license_number")
    @JsonIgnore
    private String driverLicenseNumber;

    @Column(name = "driver_license_date")
    @JsonIgnore
    private Timestamp driverLicenseDate;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    @JsonIgnore
    private String userPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "latitude")
    @JsonIgnore
    private Long latitude;

    @Column(name = "longitude")
    @JsonIgnore
    private Long longitude;

    @Column(name = "creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

    @Column(name = "is_deleted")
    @JsonIgnore
    private boolean isDeleted;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Order> orders;

}

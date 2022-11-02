package by.rusak.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

//@Data


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(exclude = {
        "roles", "cars"
})
@ToString(exclude = {
        "roles", "cars"
})
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@NamedQuery(name = "m_users_multiple_ids_search", query = "select u from User u where u.id = :userIds")
@Cacheable
public class User {

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "user_login")),
            @AttributeOverride(name = "password", column = @Column(name = "user_password"))
    })
    private Credentials credentials;


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

    @Column(name = "activation_code")
    @JsonIgnore
    private String activationCode;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    @JsonIgnore
    private Set<Role> roles;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    @JsonIgnore
    private Set<Car> cars;

}
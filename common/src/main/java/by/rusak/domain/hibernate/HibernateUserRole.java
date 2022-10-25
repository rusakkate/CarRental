package by.rusak.domain.hibernate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class HibernateUserRole {

    @Id
    @Column(name = "id_user_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "id_user")
    private Long idUser;

}

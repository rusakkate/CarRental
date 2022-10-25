package by.rusak.repository.springdata;

import by.rusak.domain.SystemRoles;
import by.rusak.domain.hibernate.HibernateRole;
import by.rusak.domain.hibernate.HibernateUserRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesSpringDataRepository extends CrudRepository<HibernateRole, Long>, JpaRepository<HibernateRole, Long> {

    HibernateRole findHibernateRoleByRoleName (SystemRoles roleName);

}

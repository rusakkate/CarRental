package by.rusak.repository;

import by.rusak.domain.Role;
import by.rusak.domain.SystemRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, JpaRepository<Role, Long> {

    Role findHibernateRoleByRoleName (SystemRoles roleName);

    @Query("select r from Role r inner join r.users u where u.id = :userId")
    List<Role> findRolesByUserid(Long userId);

}

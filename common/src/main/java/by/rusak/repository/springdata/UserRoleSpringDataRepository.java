package by.rusak.repository.springdata;

import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.domain.hibernate.HibernateUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleSpringDataRepository extends CrudRepository<HibernateUserRole, Long>,
        JpaRepository<HibernateUserRole, Long>, PagingAndSortingRepository<HibernateUserRole, Long> {
}

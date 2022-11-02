package by.rusak.repository;

import by.rusak.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>,
        JpaRepository<UserRole, Long>, PagingAndSortingRepository<UserRole, Long> {
}

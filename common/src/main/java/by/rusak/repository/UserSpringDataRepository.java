package by.rusak.repository;

import by.rusak.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserSpringDataRepository extends CrudRepository<User, Long>, JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

}

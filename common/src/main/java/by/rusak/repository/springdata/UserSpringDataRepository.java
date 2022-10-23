package by.rusak.repository.springdata;

import by.rusak.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserSpringDataRepository extends CrudRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long>, PagingAndSortingRepository<HibernateUser, Long> {
    HibernateUser findHibernateUserByUserName (String name);

   //List<HibernateUser> findByCredentialsLogin(String login);

    HibernateUser findByCredentialsLogin(String login);

}

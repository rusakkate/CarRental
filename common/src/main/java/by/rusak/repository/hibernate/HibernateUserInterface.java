package by.rusak.repository.hibernate;

import by.rusak.domain.SearchCriteria;
import by.rusak.domain.User;
import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.repository.CRUDRepository;

import java.util.Optional;

public interface HibernateUserInterface extends CRUDRepository<Long, HibernateUser> {

    Optional<User> findByLogin(String login);

    //Search criteria
    Object criteriaAPITest(SearchCriteria criteria);
}
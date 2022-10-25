package by.rusak.service;

import by.rusak.domain.hibernate.HibernateUserRole;

import java.util.Optional;

public interface UserRoleService {

    HibernateUserRole save(HibernateUserRole hibernateUserRole);

    Optional<HibernateUserRole> findById(Long userRoleId);
}

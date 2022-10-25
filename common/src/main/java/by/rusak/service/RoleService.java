package by.rusak.service;


import by.rusak.domain.SystemRoles;
import by.rusak.domain.hibernate.HibernateRole;

import java.util.Optional;

public interface RoleService {

    Optional<HibernateRole> findById(Long userId);

    HibernateRole findRoleIdByRoleName (SystemRoles roleName);
}

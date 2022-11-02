package by.rusak.service;


import by.rusak.domain.Role;
import by.rusak.domain.SystemRoles;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Long userId);

    Role findRoleIdByRoleName (SystemRoles roleName);
}

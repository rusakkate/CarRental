package by.rusak.service;

import by.rusak.domain.User;
import by.rusak.domain.UserRole;

import java.util.Optional;

public interface UserRoleService {

    UserRole save(UserRole hibernateUserRole);

    Optional<UserRole> findById(Long userRoleId);

    void setUserRoles(User user);
}

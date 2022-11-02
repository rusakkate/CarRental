package by.rusak.service;

import by.rusak.domain.SystemRoles;
import by.rusak.domain.User;
import by.rusak.domain.UserRole;
import by.rusak.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService{

    private final UserRoleRepository repository;

    private final RoleService roleService;

    @Override
    public UserRole save(UserRole userRole) {

        return repository.save(userRole);
    }

    @Override
    public Optional<UserRole> findById(Long userRoleId) {
        return repository.findById(userRoleId);
    }

    @Override
    public void setUserRoles(User user) {
        UserRole userRole = new UserRole();
        userRole.setIdUser(user.getId());
        userRole.setIdRole(roleService.findRoleIdByRoleName(SystemRoles.ROLE_USER).getId());
        save(userRole);
    }

}

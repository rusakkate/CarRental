package by.rusak.service;

import by.rusak.domain.Role;
import by.rusak.domain.SystemRoles;
import by.rusak.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository repository;

    @Override
    public Optional<Role> findById(Long roleId) {

        return repository.findById(roleId);
    }

    @Override
    public Role findRoleIdByRoleName (SystemRoles roleName) {

        return repository.findHibernateRoleByRoleName(roleName);
    }

}

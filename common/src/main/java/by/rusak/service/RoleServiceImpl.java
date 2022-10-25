package by.rusak.service;

import by.rusak.domain.SystemRoles;
import by.rusak.domain.hibernate.HibernateRole;
import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.repository.springdata.RolesSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RolesSpringDataRepository repository;

    @Override
    public Optional<HibernateRole> findById(Long roleId) {

        return repository.findById(roleId);
    }

    @Override
    public HibernateRole findRoleIdByRoleName (SystemRoles roleName) {

        return repository.findHibernateRoleByRoleName(roleName);
    }

}

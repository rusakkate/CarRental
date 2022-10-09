package by.rusak.repository.jdbctemplate;

import by.rusak.domain.Role;
import by.rusak.repository.CRUDRepository;

import java.util.List;

public interface RoleRepositoryInterface extends CRUDRepository<Long, Role> {
    List<Role> findRolesByUserId(Long userId);
}

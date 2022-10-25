package by.rusak.service;

import by.rusak.domain.hibernate.HibernateUserRole;
import by.rusak.repository.springdata.UserRoleSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService{

    private final UserRoleSpringDataRepository repository;

    @Override
    public HibernateUserRole save(HibernateUserRole hibernateUserRole) {

        return repository.save(hibernateUserRole);
    }

    @Override
    public Optional<HibernateUserRole> findById(Long userRoleId) {
        return repository.findById(userRoleId);
    }

}

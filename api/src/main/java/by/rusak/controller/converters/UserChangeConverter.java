package by.rusak.controller.converters;

import by.rusak.controller.requests.UserChangeRequest;
import by.rusak.domain.hibernate.HibernateUser;
import by.rusak.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChangeConverter extends UserBaseConverter<UserChangeRequest, HibernateUser> {

    private final UserSpringDataRepository repository;

    @Override
    public HibernateUser convert(UserChangeRequest source) {

        Optional<HibernateUser> user = repository.findById(source.getId());
        return doConvert(user.get(), source);
    }
}
package by.rusak.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository <K, T> {
    int DEFAULT_FIND_ALL_LIMIT = 10;

    int DEFAULT_FIND_ALL_OFFSET = 0;

    List<T> findAll();

    List<T> findAll(int limit, int offset);

    T create(T object);

    T findById(K id);

    T update(T object);

    K delete(K id);
}

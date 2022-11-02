package by.rusak.repository;

import by.rusak.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long>,
        PagingAndSortingRepository<User, Long> {

    Optional<User> findByCredentialsLogin(String login);

    User findByEmail(String email);



    @Query(value = "select carrental.orders.rental_start_date, carrental.orders.rental_end_date, carrental.orders.order_price, " +
            "carrental.cars.brand, carrental.cars.model " +
            "from carrental.users " +
            "join carrental.orders on carrental.users.id_user = carrental.orders.id_user " +
            "join carrental.cars on carrental.orders.id_car = carrental.cars.id_car  " +
            "where carrental.users.id_user = :id", nativeQuery = true)
    List<Object[]> findByHQLQueryNativeUserOrders(@Param("id") Long id);

    @Query(value = "select carrental.orders.rental_start_date, carrental.orders.rental_end_date, carrental.orders.order_price, " +
            "carrental.cars.brand, carrental.cars.model " +
            "from carrental.users " +
            "join carrental.orders on carrental.users.id_user = carrental.orders.id_user " +
            "join carrental.cars on carrental.orders.id_car = carrental.cars.id_car  " +
            "where carrental.users.user_login = :user_login", nativeQuery = true)
    List<Object[]> findByHQLQueryNativeUserOrdersByLogin(@Param("user_login") String login);

    // no use
    @Query(value = "select u from User u where u.id = :id")
    List<Object[]> findUserВуId(@Param("id") Long id);

    @Query(value = "select * from carrental.users where carrental.users.id_user = :id", nativeQuery = true)
    List<Object[]> findByHQLQueryNativeUserВуId1(@Param("id") Long id);

}

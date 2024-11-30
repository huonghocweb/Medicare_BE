package poly.pharmacyproject.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.Order;
import poly.pharmacyproject.Model.Entity.User;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o JOIN o.user u " +
            " WHERE u.userId =:userId ")
    Page<Order> getOrdersByUserId(Pageable pageable, @Param("userId") Integer userId);
}

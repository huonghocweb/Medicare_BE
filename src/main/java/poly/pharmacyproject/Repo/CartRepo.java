package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c JOIN c.user u " +
            " WHERE u.userId = :userId")
    Cart getCartByUserId(@Param("userId") Integer userId);
}

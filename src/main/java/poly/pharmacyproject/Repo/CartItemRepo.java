package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.CartItem;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    @Query("SELECT ci FROM CartItem ci JOIN ci.cart c " +
            " WHERE c.cartId =:cartId ")
    List<CartItem> getCartItemByCartId(@Param("cartId") Integer cartId);

    @Query("SELECT caI FROM CartItem caI JOIN caI.variation va " +
            " WHERE va.variationId =:variationId ")
    CartItem getCartItemByVaId(@Param("variationId") Integer variationId);
}

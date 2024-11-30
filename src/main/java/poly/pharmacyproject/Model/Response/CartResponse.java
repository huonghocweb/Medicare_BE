package poly.pharmacyproject.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.pharmacyproject.Model.Entity.CartItem;
import poly.pharmacyproject.Model.Entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private Integer cartId;
    private Double totalPrice;
    private Integer totalQuantity;
    private UserResponse user;
    private List<CartItemResponse> cartItems;

}

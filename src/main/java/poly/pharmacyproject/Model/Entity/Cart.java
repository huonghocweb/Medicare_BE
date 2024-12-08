package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="cart_id")
    private Integer cartId;

    @Column(name ="total_price")
    private Double totalPrice;

    @Column(name ="total_quantity")
    private Integer totalQuantity;

    @Column(name="discount_amount")
    private Double discountAmount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="coupon_id")
    private Coupon coupon;

}

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
@Table(name="cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private Integer cartItemId;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price")
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="variation_id")
    private Variation variation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
}

package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="variations")
public class Variation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="variation_id")
    private Integer variationId;

    @Column(name="sku")
    private String sku;

    @Column(name="stock_quantity")
    private Integer stockQuantity;

    @Column(name="create_at")
    private LocalDateTime createAt;

    @Column(name="update_at")
    private LocalDateTime updateAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="package_id")
    private Packaging packaging;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="dosage_form_id")
    private DosageForm dosageForm;

    @JsonIgnore
    @OneToMany(mappedBy = "variation")
    private List<OrderDetails> orderDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "variation")
    private List<OrderReturn> orderReturns;

    @JsonIgnore
    @OneToMany(mappedBy = "variation")
    private List<PriceHistory> priceHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "variation")
    private List<StockMovement> stockMovements;

    @JsonIgnore
    @OneToMany(mappedBy = "variation")
    private List<CartItem> cartItems;
}

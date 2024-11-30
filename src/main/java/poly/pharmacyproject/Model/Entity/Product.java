package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="base_price")
    private Double basePrice;

    @Column(name="price_id")
    private String priceId;


    @Column(name="description")
    private String description;

    @Column(name="create_at")
    private LocalDate createAt;

    @Column(name="update_at")
    private LocalDate updateAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<WishList> wishLists;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Variation> variations;
}

package poly.pharmacyproject.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "stock_movement")
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stock_movement_id")
    private Integer stockMovementId;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="movement_type")
    private String movementType;

    @Column(name="date_at")
    private LocalDateTime dateAt;

    @Column(name="notes")
    private String notes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="variation_id")
    private Variation variation;

}

package poly.pharmacyproject.Model.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.pharmacyproject.Model.Entity.Order;
import poly.pharmacyproject.Model.Entity.Variation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsRequest {
    private Double price;
    private Integer quantity;
    private Integer orderId;
    private Integer variationId;
}

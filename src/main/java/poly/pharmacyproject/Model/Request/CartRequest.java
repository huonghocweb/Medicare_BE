package poly.pharmacyproject.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {
    private Integer totalQuantity;
    private Double totalPrice;
    private Integer cartItemId;
}

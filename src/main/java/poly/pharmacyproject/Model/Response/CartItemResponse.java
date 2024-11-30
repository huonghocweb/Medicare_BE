package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {
    private Integer cartItemId;
    private Integer quantity;
    private Double price;
    private VariationResponse variation;
}

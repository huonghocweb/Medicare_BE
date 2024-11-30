package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponse {
    private Integer orderDetailsId;
    private Double price;
    private Integer quantity;
    private VariationResponse variation;
}

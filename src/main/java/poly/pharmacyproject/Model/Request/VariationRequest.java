package poly.pharmacyproject.Model.Request;

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
public class VariationRequest {
    private Integer variationId;
    private String sku;
    private Integer stockQuantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Integer productId;
    private Integer packagingId;
    private Integer dosageFormId;
    private List<Integer> priceHistoriesId;
    private List<Integer> stockMovementsId;
}

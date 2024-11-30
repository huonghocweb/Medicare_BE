package poly.pharmacyproject.Model.Response;

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
public class VariationResponse {
    private Integer variationId;
    private String sku;
    private Integer stockQuantity;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private ProductResponse product;
    private PackagingResponse packaging;
    private DosageFormResponse dosageForm;
    private List<PriceHistoryResponse> priceHistories;
    private List<StockMovementResponse> stockMovements;
}

package poly.pharmacyproject.Model.Response;

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
public class StockMovementResponse {
    private Integer stockMovementId;
    private Integer quantity;
    private String movementType;
    private LocalDateTime dateAt;
    private String notes;
}

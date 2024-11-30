package poly.pharmacyproject.Model.Response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusResponse {
    private Integer orderStatusId;
    private String orderStatusName;
}

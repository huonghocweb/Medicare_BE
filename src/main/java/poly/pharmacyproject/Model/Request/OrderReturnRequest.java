package poly.pharmacyproject.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReturnRequest {
    private LocalDateTime returnDateTime;
    private String reason;
    private String status;
    private Integer orderId;
}

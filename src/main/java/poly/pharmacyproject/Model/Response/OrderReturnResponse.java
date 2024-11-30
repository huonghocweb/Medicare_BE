package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReturnResponse {
    private Integer orderReturnId;
    private LocalDateTime returnDateTime;
    private String reason;
    private String status;
    private OrderResponse order;
}

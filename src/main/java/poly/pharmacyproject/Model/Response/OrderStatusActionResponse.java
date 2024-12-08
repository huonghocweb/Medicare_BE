package poly.pharmacyproject.Model.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusActionResponse {
    private Integer orderStatusActionId;
    private String orderStatusActionName;
    private String orderStatusActionEndpoint;
}

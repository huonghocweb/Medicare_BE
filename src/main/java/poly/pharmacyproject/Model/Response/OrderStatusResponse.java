package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusResponse {
    private Integer orderStatusId;
    private String orderStatusName;
    private List<OrderStatusActionResponse> orderStatusActions;
}

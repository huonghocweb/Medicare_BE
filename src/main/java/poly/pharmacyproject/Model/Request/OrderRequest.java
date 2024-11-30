package poly.pharmacyproject.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
        private Integer orderId;
        private LocalDate orderDate;
        private LocalTime orderTime;
        private String deliveryAddress;
        private LocalDateTime updateAt;
        private Double totalPrice;
        private Integer totalQuantity;
        private Integer shipFee;
        private Integer leadTime;
        private LocalDateTime paymentDatetime;
        private LocalDateTime estimatedDeliveryDateTime;
        private Integer paymentMethodId;
        private Integer userId;
        private Integer orderStatusId;
        private Integer couponId;
        private List<Integer> orderDetailsId;
        private List<Integer> orderReturnsId;

}

package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.pharmacyproject.Model.Entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
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
        private PaymentMethodResponse paymentMethod;
        private UserResponse user;
        private OrderStatusResponse orderStatus;
        private CouponResponse coupon;
        private List<OrderDetailsResponse> orderDetails;
        private List<OrderReturnResponse> orderReturns;

}

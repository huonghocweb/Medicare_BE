package poly.pharmacyproject.Mapper;

import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import poly.pharmacyproject.Model.Entity.Order;
import poly.pharmacyproject.Model.Request.OrderRequest;
import poly.pharmacyproject.Model.Response.OrderResponse;
import poly.pharmacyproject.Repo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",uses = {
        OrderDetailsMapper.class,
        OrderReturnMapper.class,
        OrderStatusMapper.class,
        CouponMapper.class,
        DeliveryAddressMapper.class,
        PaymentMethodMapper.class,
        UserMapper.class,
        OrderStatusActionMapper.class
})
public interface OrderMapper {
    OrderResponse convertEnToRes(Order order);

    Order convertReqToEn(OrderRequest orderRequest);

//    public OrderResponse convertEnToRes(Order order){
//            return OrderResponse.builder()
//                    .orderId(order.getOrderId())
//                    .orderDate(order.getOrderDate())
//                    .orderTime(order.getOrderTime())
//                    .coupon(order.getCoupon() != null ? couponMapper.convertEnToResponse(order.getCoupon()) : null)
//                    .orderStatus(orderStatusMapper.convertEnToRes(order.getOrderStatus()))
//                    .deliveryAddress(order.getDeliveryAddress())
//                    .shipFee(order.getShipFee())
//                    .leadTime(order.getLeadTime())
//                    .paymentDatetime(order.getPaymentDatetime())
//                    .estimatedDeliveryDateTime(order.getEstimatedDeliveryDateTime())
//                    .orderDetails(order.getOrderDetails() != null ?
//                            order.getOrderDetails().stream()
//                                    .map(orderDetailsMapper :: convertEnToRes)
//                                    .collect(Collectors.toList()) : null)
//                    .paymentMethod(paymentMethodMapper.convertEnToRes(order.getPaymentMethod()))
//                    .user(userMapper.convertEnToRes(order.getUser()))
//                    .totalPrice(order.getTotalPrice())
//                    .totalQuantity(order.getTotalQuantity())
//                    .updateAt(order.getUpdateAt())
//                    .build();
//        }
//        public Order convertReqToEn(OrderRequest orderRequest){
//            return Order.builder()
//                    .orderDate(orderRequest.getOrderDate() != null ? orderRequest.getOrderDate() : LocalDate.now())
//                    .orderTime(orderRequest.getOrderTime() != null ? orderRequest.getOrderTime() : LocalTime.now())
//                    .deliveryAddress(orderRequest.getDeliveryAddress())
//                    .totalPrice(orderRequest.getTotalPrice())
//                    .totalQuantity(orderRequest.getTotalQuantity())
//                    .updateAt(LocalDateTime.now())
//                    .shipFee(orderRequest.getShipFee())
//                    .leadTime(orderRequest.getLeadTime())
//                    .paymentDatetime(orderRequest.getPaymentDatetime())
//                    .estimatedDeliveryDateTime(orderRequest.getEstimatedDeliveryDateTime())
//                    .paymentMethod(paymentMethodRepo.findById(orderRequest.getPaymentMethodId())
//                            .orElseThrow(() -> new EntityNotFoundException("Not Found Payment Method")))
//                    .orderStatus(orderStatusRepo.findById(orderRequest.getOrderStatusId())
//                            .orElseThrow(() -> new EntityNotFoundException("Not Found Order Status")))
//                   .coupon(orderRequest.getCouponId() != null ? couponRepo.findById(orderRequest.getCouponId())
//                            .orElseThrow(()-> new EntityNotFoundException("Not Found Coupon ")) : null)
//                    .user(userRepo.findById(orderRequest.getUserId())
//                            .orElseThrow(() -> new EntityNotFoundException("Not found User")))
////                    .orderDetails(orderRequest.getOrderDetailsId() != null ?
////                            orderRequest.getOrderDetailsId().stream()
////                                    .map(oderDetailsId -> orderDetailsRepo.findById(oderDetailsId)
////                                            .orElseThrow(() -> new EntityNotFoundException("Not found OrderDetails")))
////                                    .collect(Collectors.toList()) : null
////                                    )
//                    .build();
//        }
//
//        public OrderRequest convertResToReq(OrderResponse orderResponse){
//            return OrderRequest.builder()
//                    .totalPrice(orderResponse.getTotalPrice())
//                    .totalQuantity(orderResponse.getTotalQuantity())
//                    .deliveryAddress(orderResponse.getDeliveryAddress())
//                    .orderDate(orderResponse.getOrderDate())
//                    .orderTime(orderResponse.getOrderTime())
//                    .shipFee(orderResponse.getShipFee())
//                    .leadTime(orderResponse.getLeadTime())
//                    .paymentDatetime(orderResponse.getPaymentDatetime())
//                    .estimatedDeliveryDateTime(orderResponse.getEstimatedDeliveryDateTime())
//                    .userId(orderResponse.getUser().getUserId())
//                    .couponId(orderResponse.getCoupon() != null ?orderResponse.getCoupon().getCouponId() : null)
//                    .paymentMethodId(orderResponse.getPaymentMethod().getPaymentMethodId())
//                    .orderStatusId(orderResponse.getOrderStatus().getOrderStatusId())
////                    .orderDetailsId(orderResponse.getOrderDetails() != null ?
////                            orderResponse.getOrderDetails().stream()
////                                    .map(OrderDetailsResponse::getOrderDetailsId)
////                                    .collect(Collectors.toList()) : null)
//                    .build();
//        }
}

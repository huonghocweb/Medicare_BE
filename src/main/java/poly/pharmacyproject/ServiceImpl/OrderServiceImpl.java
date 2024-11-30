package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.OrderMapper;
import poly.pharmacyproject.Model.Entity.Order;
import poly.pharmacyproject.Model.Request.OrderRequest;
import poly.pharmacyproject.Model.Response.OrderResponse;
import poly.pharmacyproject.Repo.*;
import poly.pharmacyproject.Service.OrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;
    @Autowired
    private OrderStatusRepo orderStatusRepo;
    @Autowired
    private CouponRepo couponRepo;

    @Override
    public Page<OrderResponse> getAllOrder(Pageable pageable) {
        Page<Order> orderPage = orderRepo.findAll(pageable);
        List<OrderResponse> orderResponses = orderPage.getContent().stream()
                .map(orderMapper :: convertEnToRes)
                .toList();
        return new PageImpl<>(orderResponses,pageable, orderPage.getTotalElements());
    }

    @Override
    public OrderResponse getOrderByOrderId(Integer orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("not found Order "));
        return orderMapper.convertEnToRes(order);
    }

    @Override
    public Page<OrderResponse> getOrdersByUserId(Pageable pageable, Integer userId) {
        Page<Order> orderPage = orderRepo.getOrdersByUserId(pageable, userId);
        List<OrderResponse> orderResponses =orderPage.getContent().stream()
                .map(orderMapper :: convertEnToRes)
                .toList();
        return new PageImpl<>(orderResponses,pageable, orderPage.getTotalElements());
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.convertReqToEn(orderRequest);
        order.setUser(orderRequest.getUserId() != null ?  userRepo.findById(orderRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("not found User")) : null);
        order.setPaymentMethod(orderRequest.getPaymentMethodId() != null ? paymentMethodRepo.findById(orderRequest.getPaymentMethodId())
                .orElseThrow(() -> new EntityNotFoundException("not found Payment Method")) : null);
        order.setPaymentDatetime(LocalDateTime.now());
        order.setOrderTime(LocalTime.now());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(orderRequest.getOrderStatusId() != null ?
                orderStatusRepo.findById(orderRequest.getOrderStatusId())
                        .orElseThrow(() -> new EntityNotFoundException("not found Order Status")) : null);
        order.setCoupon(orderRequest.getCouponId() != null ?
                couponRepo.findById(orderRequest.getCouponId())
                        .orElseThrow(() -> new EntityNotFoundException("not found Coupon")): null);
        Order orderCreated = orderRepo.save(order);
        return orderMapper.convertEnToRes(orderCreated);
    }

    @Override
    public OrderResponse updateOrder(Integer orderId, OrderRequest orderRequest) {
        return orderRepo.findById(orderId).map(orderExists -> {
            Order order = orderMapper.convertReqToEn(orderRequest);
            order.setOrderId(orderExists.getOrderId());
            order.setUser(orderRequest.getUserId() != null ?  userRepo.findById(orderRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("not found User")) : null);
            order.setPaymentMethod(orderRequest.getPaymentMethodId() != null ? paymentMethodRepo.findById(orderRequest.getPaymentMethodId())
                    .orElseThrow(() -> new EntityNotFoundException("not found Payment Method")) : null);
            order.setPaymentDatetime(LocalDateTime.now());
            order.setOrderTime(LocalTime.now());
            order.setOrderDate(LocalDate.now());
            order.setOrderStatus(orderRequest.getOrderStatusId() != null ?
                    orderStatusRepo.findById(orderRequest.getOrderStatusId())
                            .orElseThrow(() -> new EntityNotFoundException("not found Order Status")) : null);
            order.setCoupon(orderRequest.getCouponId() != null ?
                    couponRepo.findById(orderRequest.getCouponId())
                            .orElseThrow(() -> new EntityNotFoundException("not found Coupon")): null);
            Order orderUpdated = orderRepo.save(order);
            return orderMapper.convertEnToRes(orderUpdated);
        }).orElseThrow(() -> new EntityNotFoundException("not found Order"));
    }
}

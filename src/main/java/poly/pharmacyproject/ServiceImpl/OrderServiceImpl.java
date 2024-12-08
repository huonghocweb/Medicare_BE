package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.OrderMapper;
import poly.pharmacyproject.Model.Entity.Order;
import poly.pharmacyproject.Model.Enum.CommonOrderStatus;
import poly.pharmacyproject.Model.Request.OrderRequest;
import poly.pharmacyproject.Model.Response.OrderResponse;
import poly.pharmacyproject.Repo.*;
import poly.pharmacyproject.Service.OrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
        if  (pageable.getPageNumber()>= (orderPage.getTotalPages() -1) ){
            pageable = PageRequest.of(orderPage.getTotalPages()-1, pageable.getPageSize(), pageable.getSort());
        }
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
        Page<Order> orderPage  =  orderRepo.getOrdersByUserId(pageable, userId);
        if  (pageable.getPageNumber()>= (orderPage.getTotalPages() -1) ){
              pageable = PageRequest.of(orderPage.getTotalPages()-1, pageable.getPageSize(), pageable.getSort());
        }
        List<OrderResponse> orderResponses =orderPage.getContent().stream()
                .map(orderMapper :: convertEnToRes)
                .toList();
        return new PageImpl<>(orderResponses,pageable, orderPage.getTotalElements());
    }

    @Override
    public Page<OrderResponse> getOrdersByOrderStatusId(Pageable pageable, Integer orderStatusId, Integer userId) {
        Page<Order> orderPage = orderRepo.getOrdersByOrderStatusId(pageable, orderStatusId, userId);
        if  (pageable.getPageNumber()>= (orderPage.getTotalPages() -1) ){
            pageable = PageRequest.of(orderPage.getTotalPages()-1, pageable.getPageSize(), pageable.getSort());
        }
        List<OrderResponse> orderResponses = orderPage.getContent().stream()
                .map(orderMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return new PageImpl<>(orderResponses,pageable,orderPage.getTotalElements());
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

    @Override
    public OrderResponse updateOrderStatus(Integer orderId , Integer orderStatusId){
        return orderRepo.findById(orderId).map(orderExists -> {
            orderExists.setOrderStatus(orderStatusRepo.findById(orderStatusId)
                    .orElseThrow(() -> new EntityNotFoundException("not found Order Status")));
            return orderMapper.convertEnToRes(orderRepo.save(orderExists));
        }).orElseThrow(() -> new EntityNotFoundException("not found Order"));
    }

    @Override
    public OrderResponse cancelOrder(Integer orderId){
         return this.updateOrderStatus(orderId, CommonOrderStatus.CANCEL.getId());
    }

    @Override
    public OrderResponse completeOrder(Integer orderId){
        return this.updateOrderStatus(orderId, CommonOrderStatus.COMPLETE.getId());
    }

    public OrderResponse returnRequest(Integer orderId){
        return this.updateOrderStatus(orderId, CommonOrderStatus.RETURN_REQUEST.getId());
    }

    public OrderResponse acceptReturnRequest(Integer orderId , Boolean isAccept){
            if(isAccept){
                return  this.updateOrderStatus(orderId, CommonOrderStatus.RETURNED.getId());
            }else{
                return this.updateOrderStatus(orderId, CommonOrderStatus.COMPLETE.getId());
            }
    }

}

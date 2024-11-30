package poly.pharmacyproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Request.OrderRequest;
import poly.pharmacyproject.Model.Response.OrderResponse;

@Service
public interface OrderService {

    Page<OrderResponse> getAllOrder(Pageable pageable);
    OrderResponse getOrderByOrderId(Integer orderId);
    Page<OrderResponse> getOrdersByUserId(Pageable pageable, Integer userId);
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(Integer orderId, OrderRequest orderRequest);
}

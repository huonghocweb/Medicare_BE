package poly.pharmacyproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Request.OrderDetailsRequest;
import poly.pharmacyproject.Model.Response.OrderDetailsResponse;

import java.util.List;

@Service
public interface OrderDetailsService {
    Page<OrderDetailsResponse> getAllOrderDetails(Pageable pageable);
    List<OrderDetailsResponse> getOrderDetailsByOrderId(Integer orderId);
    OrderDetailsResponse createOrderDetails(OrderDetailsRequest orderDetailsRequest);
    OrderDetailsResponse updateOrderDetails(Integer orderDetailsId, OrderDetailsRequest orderDetailsRequest);
}

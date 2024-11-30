package poly.pharmacyproject.Service;

import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.OrderStatusResponse;

import java.util.List;

@Service
public interface OrderStatusService {
    List<OrderStatusResponse> getAllOrderStatus ();
}

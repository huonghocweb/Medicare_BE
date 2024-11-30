package poly.pharmacyproject.Service;

import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.OrderDetailsResponse;
import poly.pharmacyproject.Model.Response.OrderResponse;

import java.io.IOException;
import java.util.List;

@Service
public interface PaymentService {

     OrderResponse createOrder(Integer userId, Integer couponId , Double totalPrice , Integer paymentMethodId , String deliveryAddress , Integer shipFee);
     List<OrderDetailsResponse> createOrderDetails(Integer orderId, Integer userId);
     OrderResponse updateOrderPaymentStatus(Integer orderId , Integer orderStatusId);
     void sendMail(OrderResponse  order) throws IOException, WriterException, MessagingException;
     void updateStockAndCouponStorage(OrderResponse order) ;
}

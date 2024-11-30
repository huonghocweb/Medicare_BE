package poly.pharmacyproject.ServiceImpl;


import com.google.zxing.WriterException;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Service.StripeService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class StripeServiceImpl {
    @Autowired
    private StripeService stripeService;
//    @Autowired
//    private PaymentService paymentService;
//    @Autowired
//    private CartService cartService;
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private OrderDetailsService orderDetailsService;

    public String createPaymentUrlByStripe(Integer orderInfo, Integer totalPrice, String baseReturnUrl , List<Map<String,Object>> cartItems) throws StripeException {
        String paymentUrl;
        try {
            paymentUrl = stripeService.createCheckoutSession(orderInfo,totalPrice,baseReturnUrl,cartItems);
        } catch (StripeException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        return paymentUrl;
    }

//        public PaymentInfo returnPaymentByStripe(HttpServletRequest request) throws IOException, WriterException {
//        Session session = stripeService.returnPayment(request.getParameter("session_id"));
//        Integer paymentStatus ;
//        String orderInfo_Parameter =session.getMetadata().get("orderInfo");
//        String totalPrice_Parameter  =session.getMetadata().get("totalPrice");
//        String dateTime_Parameter = session.getMetadata().get("dateTime");
//        String transactionId_Parameter =  session.getId();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        LocalDateTime dateTime = LocalDateTime.parse(dateTime_Parameter, formatter);
//        String jwtToken = request.getHeader("Authorization").substring(7);
//        String username = jwtUtils.extractUsername(jwtToken);
//            UserResponse userResponse = userService.getUserByUsername(username)
//                    .orElseThrow(() -> new EntityNotFoundException("Not found User"));
//        if(session.getPaymentStatus().equals("paid")){
//            paymentStatus=1;
//             OrderResponse orderResponse = paymentService.updatePaymentSuccess(Integer.valueOf(orderInfo_Parameter));
//             List<OrderDetailsResponse> orderDetailsResponses = orderDetailsService.getOrderDetailsByOrderId(Integer.valueOf(orderInfo_Parameter));
//             paymentService.sendEmail(username, orderResponse, orderDetailsResponses);
//            // Xử lý cập nhật nghiệp vụ coupon
//          //  paymentService.updateCouponStorageAndUsedCount(username, couponId);
//        }else{
//            paymentStatus =0;
//        }
//        // Xóa thông tin giỏ hàng khi hoàn tất
//        cartService.removeCart(userResponse.getUserId());
//        return paymentService.createPaymentInfo(orderInfo_Parameter, paymentStatus, dateTime_Parameter , totalPrice_Parameter , transactionId_Parameter );
//    }
}


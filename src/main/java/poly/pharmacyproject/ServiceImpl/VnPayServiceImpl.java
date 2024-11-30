package poly.pharmacyproject.ServiceImpl;


import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.OrderMapper;
import poly.pharmacyproject.Model.Entity.Order;
import poly.pharmacyproject.Model.Response.OrderResponse;
import poly.pharmacyproject.Repo.OrderRepo;
import poly.pharmacyproject.Repo.OrderStatusRepo;
import poly.pharmacyproject.Service.PaymentService;
import poly.pharmacyproject.Service.VnPayService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

@Service
public class VnPayServiceImpl {

    @Autowired
    private VnPayService vnPayService;
    @Autowired
    private PaymentService paymentService;

    // Tạo giao dịch bằng và trả về đường dẫn giúp dẫn tới trang thanh toán của VnPay
    public String createPaymentUrl(int totalPrice, String orderInfo, String returnUrl ) throws UnsupportedEncodingException {
        System.out.println("Total Price  VN Pay : " + totalPrice);
        String paymentUrl= vnPayService.createOrder(totalPrice, orderInfo, returnUrl );
        System.out.println("paymentUrl : " + returnUrl);
        return paymentUrl;
    }

//    public PaymentInfo returnPayment(HttpServletRequest request) throws IOException, ParseException, WriterException {
//        PaymentInfo  paymentInfo = new PaymentInfo();
//        // request chứa thông tin sẽ được đưa vào hàm orderReturn để kiếm tra bảo mật và cập nhật trạng thái
//        // Sau đó trả về trạng thái thanh toán và các thông tin liên quan .
//        int paymentStatus = vnPayService.orderReturn(request);
//        // tao dto paymentInfo de lay du lieu payment cho font-end ;
//        request.setCharacterEncoding("UTF-8");
//        String totalPrice =request.getParameter("vnp_Amount");
//        String vnp_PayDate = request.getParameter("vnp_PayDate");
//        String vnp_OrderInfo= request.getParameter("vnp_OrderInfo");
//        String vnp_TransactionId = request.getParameter("vnp_TransactionId");
//        // OrderInfo do nguoi dung tu dinh nghia, o day dinh nghia la cardId == key trong Map<CartId,Cart> MapStore
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//       // LocalDateTime dateTime = LocalDateTime.parse(vnp_PayDate, formatter);
//        String jwtToken = request.getHeader("Authorization").substring(7);
//        UserResponse user= userService.getUserByUsername(jwtUtils.extractUsername(jwtToken))
//                .orElseThrow(() -> new EntityNotFoundException("not found User"));
//        Integer orderId = Integer.valueOf(vnp_OrderInfo);
//        if(paymentStatus ==1 ){
//            System.out.println("Payment Success");
//            paymentInfo = paymentService.createPaymentInfo(vnp_OrderInfo, paymentStatus, vnp_PayDate, totalPrice, vnp_TransactionId);
//            // Xử lý Coupon usedCount và CouponStorage
////           paymentService.updateCouponStorageAndUsedCount(username,couponId);
//            // Nếu thanh toán thành công chuyển trạng thái thành Shipping
//            OrderResponse orderResponse = paymentService.updatePaymentSuccess(orderId);
//            List<OrderDetailsResponse> orderDetailsResponses = orderDetailsService.getOrderDetailsByOrderId(orderId);
//            paymentService.sendEmail(user.getUserName(),orderResponse ,orderDetailsResponses );
//            System.out.println("Payment Success");
//        }else{
//            System.out.println("Payment Failed");
//            paymentInfo.setPaymentStatus(0);
//        }
//        cartService.removeCart(user.getUserId());
//        System.out.println(paymentInfo + "Payment Info Return");
//        return paymentInfo;
//    }

    public OrderResponse returnPayment(HttpServletRequest request) throws ParseException, MessagingException, IOException, WriterException {
        int isPaymentSuccess = vnPayService.orderReturn(request);
        String transactionId = request.getParameter("vnp_TransactionId");
        Integer orderId = Integer.valueOf(request.getParameter("vnp_OrderInfo"));
        if(isPaymentSuccess ==1 ){
            System.out.println("Payment Success");
            OrderResponse orderUpdated = paymentService.updateOrderPaymentStatus(orderId, 2);
            paymentService.sendMail(orderUpdated);
            paymentService.updateStockAndCouponStorage(orderUpdated);
            return orderUpdated;
        }else {
            System.out.println("Payment Failed");
            return null;
        }
    }
}

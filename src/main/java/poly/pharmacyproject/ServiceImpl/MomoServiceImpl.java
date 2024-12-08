package poly.pharmacyproject.ServiceImpl;


import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.OrderMapper;
import poly.pharmacyproject.Model.Entity.Order;
import poly.pharmacyproject.Model.Response.OrderResponse;
import poly.pharmacyproject.Repo.OrderRepo;
import poly.pharmacyproject.Service.CartService;
import poly.pharmacyproject.Service.MomoService;
import poly.pharmacyproject.Service.PaymentService;

import java.util.Map;
import java.util.Objects;


@Service
public class MomoServiceImpl {

    @Autowired
    MomoService momoService;
    @Autowired
    HttpSession session;
    @Autowired
    private CartService cartService;
    @Autowired
    PaymentService paymentService;
    String orderIdByMomo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderMapper orderMapper;

    public String createUrlPaymentMomo( Integer orderInfo,Integer totalPrice, String baseUrlReturn,String username){
        String urlPayment = momoService.createPaymentRequest( orderInfo, totalPrice, baseUrlReturn,username);
        return urlPayment;
    }

     // Map<String,Object> requestBody : IPN là cách hoạt động đúng của MOMO để xác thực thanh toán , nhưng yêu cầu url công khai
    public OrderResponse verifyPayment(Map<String,String> inpData) throws Exception {
        System.out.println(inpData);
        // Đưa ipnData mà Momo trả vef thông qua inpURL public vào hàm verifyPayment để tiến hành xác thực chữ ký giao dịch
        Integer verifyStatus = momoService.verifyPayment(inpData);
        // Lấy ra các tham số từ ipnData
        String orderInfo_Parameter = inpData.get("orderInfo");
        String paymentDateTime_Parameter = inpData.get("responseTime");
        String transactionId_Parameter = inpData.get("transId");
        String totalPrice_Parameter = inpData.get("amount");
        Integer orderId = Integer.valueOf(orderInfo_Parameter);
        // Nếu trả về 0 là xác thực giao dịch thành công với momo
        System.out.println("verifyStatus : " + verifyStatus);
        OrderResponse order = null;
        if (verifyStatus == 0) {
            System.out.println("Verify Success! , Founded Payment");
            // Trong request momo trả về có 1 trường result code là trạng thái thanh toán ==0 là thanh toán thành công
            if (inpData.get("resultCode").equals("0")) {
                System.out.println("Payment Success !");
                order = paymentService.updateOrderPaymentStatus(orderId, 2);
                paymentService.sendMail(order);
                paymentService.updateStockAndCouponStorage(order);
            } else {
                System.out.println("Payment Failed");
                order = paymentService.updateOrderPaymentStatus(orderId, 1);
            }
            orderIdByMomo = inpData.get("orderId");
            System.out.println(orderIdByMomo);
            // Trả về 1 là trường hợp không xác thực được giao dịch
        } else if (verifyStatus == 1) {
            System.out.println("Verify Failed! ,Not found Payment .");
        }
        return order;
    }

    public OrderResponse returnPayment(HttpServletRequest request){
        String orderId_request = request.getParameter("orderId");
        Integer orderId = Integer.valueOf(orderId_request);
        System.out.println("orderId_request : " + orderId_request);
        System.out.println("orderPaymentId : " + orderIdByMomo);
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("not found Order"));
        if(Objects.equals(orderIdByMomo, orderId_request)){
            return orderMapper.convertEnToRes(order);
        }
        return null;
    }

}


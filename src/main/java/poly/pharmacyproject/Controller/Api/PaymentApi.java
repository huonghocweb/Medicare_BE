package poly.pharmacyproject.Controller.Api;

import com.paypal.base.rest.PayPalRESTException;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Model.Response.OrderDetailsResponse;
import poly.pharmacyproject.Model.Response.OrderResponse;
import poly.pharmacyproject.Service.*;
import poly.pharmacyproject.ServiceImpl.MomoServiceImpl;
import poly.pharmacyproject.ServiceImpl.PayPalServiceImpl;
import poly.pharmacyproject.ServiceImpl.StripeServiceImpl;
import poly.pharmacyproject.ServiceImpl.VnPayServiceImpl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment")
public class PaymentApi {
    @Autowired
    private OrderService orderService;
    @Autowired
    private VnPayServiceImpl vnPayService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PayPalServiceImpl payPalService;
    @Autowired
    private StripeServiceImpl stripeService;
    @Autowired
    private MomoServiceImpl momoService;

    @PostMapping("/{userId}/{paymentMethodId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> paymentRequest(
            @PathVariable("userId") Integer userId,
            @PathVariable( "paymentMethodId" ) Integer paymentMethodId,
            @RequestPart(value = "totalPrice", required = false) Integer totalPrice,
            @RequestPart(value = "couponId", required = false) Integer couponId,
            @RequestPart(value = "deliveryAddress",required = false) String deliveryAddress,
            @RequestPart(value = "shipFee" , required = false) Integer shipFee,
            @RequestPart(value = "cartItems" , required = false) List<Map<String, Object>> cartItems,
            @RequestPart(value = "baseReturnUrl",required = false) String baseReturnUrl
    ) throws PayPalRESTException, UnsupportedEncodingException, StripeException {
        Map<String,Object> result = new HashMap<>();
      //  System.out.println("PaymentMethod Id " + paymentMethodId + "TotalPrice : " + totalPrice + "URl : " + baseReturnUrl);
        OrderResponse order = paymentService.createOrder(userId, couponId, Double.valueOf(totalPrice), paymentMethodId, deliveryAddress,shipFee);
        List<OrderDetailsResponse> orderDetailsList = paymentService.createOrderDetails(order.getOrderId(), userId);
        String url = "";
        if(paymentMethodId ==1 ){
            System.out.println("payment By VN Pay : ");
            url = vnPayService.createPaymentUrl(totalPrice, String.valueOf(order.getOrderId()), baseReturnUrl);
        }else if(paymentMethodId ==2) {
            System.out.println("payment By Pay Pal : ");
            url = payPalService.createPaymentUrl(totalPrice, order.getOrderId(), baseReturnUrl,  baseReturnUrl);
        }else if(paymentMethodId ==3){
            System.out.println("payment By Stripe : ");
            url =  stripeService.createPaymentUrlByStripe(order.getOrderId(), totalPrice, baseReturnUrl, cartItems);
        }else if(paymentMethodId ==4 ){
            System.out.println("payment By Momo: ");
            url = momoService.createUrlPaymentMomo(order.getOrderId(), totalPrice, baseReturnUrl, order.getUser().getUserName() );
        }
        System.out.println("URL Pay  : " + url);
        try {
            result.put("success",true);
            result.put("message","Create URL To Payment Sandbox");
            result.put("data",url);
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getPaymentInfo/{paymentMethod}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getPaymentInfo(
            @PathVariable("paymentMethod") String paymentMethod ,
            HttpServletRequest request
    ){
        Map<String,Object> result = new HashMap<>();
        System.out.println("Count ");
        try {
            result.put("success", "true");
            result.put("message", "Get Payment Info ");
            if(Objects.equals(paymentMethod, "vnpay")){
                System.out.println("GEt Payment INFO BY VN PAY");
                result.put("data",vnPayService.returnPayment(request));
            }else {
                result.put("data" ,null);
            }
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}

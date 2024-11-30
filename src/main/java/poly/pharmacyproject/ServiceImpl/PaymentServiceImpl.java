package poly.pharmacyproject.ServiceImpl;

import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.OrderMapper;
import poly.pharmacyproject.Model.Entity.*;
import poly.pharmacyproject.Model.Request.OrderDetailsRequest;
import poly.pharmacyproject.Model.Request.OrderRequest;
import poly.pharmacyproject.Model.Response.*;
import poly.pharmacyproject.Repo.*;
import poly.pharmacyproject.Service.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderStatusRepo orderStatusRepo;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private VariationRepo variationRepo;

    public OrderResponse createOrder(Integer userId, Integer couponId , Double totalPrice , Integer paymentMethodId , String deliveryAddress , Integer shipFee){
        OrderRequest orderRequest = new OrderRequest();
        Cart cart = cartRepo.getCartByUserId(userId);
        User user = userRepo.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("Not found User"));
        orderRequest.setOrderStatusId(1);
        orderRequest.setCouponId(couponId);
        orderRequest.setTotalPrice(totalPrice);
        orderRequest.setPaymentMethodId(paymentMethodId);
        orderRequest.setDeliveryAddress(deliveryAddress);
        orderRequest.setTotalQuantity(cart.getTotalQuantity());
        orderRequest.setShipFee(shipFee);
        orderRequest.setUserId(userId);
        return orderService.createOrder(orderRequest);
    }

    public List<OrderDetailsResponse> createOrderDetails(Integer orderId, Integer userId){
        Cart cart = cartRepo.getCartByUserId(userId);
        List<OrderDetailsResponse> orderDetailsList = new ArrayList<>();
        cart.getCartItems().forEach(cartItem -> {
            OrderDetailsRequest orderDetailsRequest = new OrderDetailsRequest();
            orderDetailsRequest.setOrderId(orderId);
            orderDetailsRequest.setPrice(cartItem.getPrice());
            orderDetailsRequest.setQuantity(cartItem.getQuantity());
            orderDetailsRequest.setVariationId(cartItem.getVariation().getVariationId());
            orderDetailsList.add(orderDetailsService.createOrderDetails(orderDetailsRequest));
        });
        return orderDetailsList;
    }

    public OrderResponse updateOrderPaymentStatus(Integer orderId , Integer orderStatusId){
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Not found Order"));
        order.setOrderStatus(orderStatusRepo.findById(orderStatusId)
                .orElseThrow(() -> new EntityNotFoundException("not found OrderStatus")));
        Order orderUpdated = orderRepo.save(order);
        return orderMapper.convertEnToRes(orderUpdated);
    }

    public void sendMail(OrderResponse  order) throws IOException, WriterException, MessagingException {
        MailInfo mail = new MailInfo();
        mail.setTo("huong18t4vsl1@gmail.com");
        mail.setSubject("Your Invoices Information ");
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("<html><body>");
        bodyBuilder.append("<p>Kính chào quý khách,</p>");
        bodyBuilder.append("<p>Cảm ơn quý khách đã mua hàng tại MediCare Store. Dưới đây là thông tin hóa đơn của quý khách:</p>");
        bodyBuilder.append("<p><strong>Tên khách hàng:</strong> ").append(order.getUser().getUserName()).append("<br>");
        bodyBuilder.append("<strong>Email:</strong> ").append(order.getUser().getEmail()).append("<br>");
        bodyBuilder.append("<strong>Số điện thoại:</strong> 0").append(order.getUser().getPhoneNumber()).append("<br>");
        bodyBuilder.append("<strong>Tổng tiền:</strong> ").append(order.getTotalPrice()).append(" VND</p>");
        bodyBuilder.append("<p>Thông tin chi tiết:</p>");
        bodyBuilder.append("<ul>");

        // Vòng lặp để hiển thị từng sản phẩm
        order.getOrderDetails().forEach(orderDetail -> {
            bodyBuilder.append("<li><strong>Tên Sản Phẩm:</strong> ")
                    .append(orderDetail.getVariation().getProduct().getProductName())
                    .append("<br><strong>Số Lượng:</strong> ").append(orderDetail.getQuantity())
                    .append("<br><strong>Giá Tiền:</strong> ").append(orderDetail.getPrice())
                    .append(" VND</li>");
        });
        bodyBuilder.append("</ul>");
        bodyBuilder.append("<p>Quý khách có thể quét Mã QR để xem hóa đơn chi tiết.</p>");
        bodyBuilder.append("<p>Chúc quý khách có một ngày vui vẻ!</p>");
        bodyBuilder.append("<p>Trân trọng,<br>Công ty MediCare Store.</p>");
        bodyBuilder.append("</body></html>");

        mail.setBody(bodyBuilder.toString());
        mail.setTo(order.getUser().getEmail());
        List<File> files = new ArrayList<>();
        File file= qrCodeService.createQrCodeWithFileTemp("OrderDetails", 80, 80);
        files.add(file);
        mail.setFiles(files);
        mail.setSubject("Hóa đơn mua hàng");
        mailService.send(mail);
    }

    public void updateStockAndCouponStorage(OrderResponse order){
        order.getOrderDetails().forEach(orderDetails -> {
            Variation variation = variationRepo.findById(orderDetails.getVariation().getVariationId())
                    .orElseThrow(() -> new EntityNotFoundException("Not found variation"));
            variation.setStockQuantity(variation.getStockQuantity() - orderDetails.getQuantity());
            System.out.println("UPdated Quatity Stock");
            variationRepo.save(variation);
        });
    }
}

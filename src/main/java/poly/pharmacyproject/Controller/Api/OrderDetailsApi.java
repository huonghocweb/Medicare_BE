package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Service.OrderDetailsService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsApi {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/getByOrderId/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getOrderByOrderId(
            @PathVariable("orderId") Integer orderId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","get OrderDetails By Order Id");
            result.put("data",orderDetailsService.getOrderDetailsByOrderId(orderId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}

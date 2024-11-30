package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.pharmacyproject.Service.PaymentMethodService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/paymentMethod")
public class PaymentMethodApi {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getAllPaymentMethod(
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Get All Payment Method");
            result.put("data",paymentMethodService.getAllPaymentMethod());
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}

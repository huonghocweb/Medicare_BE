package poly.pharmacyproject.Service;

import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.PaymentMethodResponse;

import java.util.List;

@Service
public interface PaymentMethodService {
    List<PaymentMethodResponse> getAllPaymentMethod();
}

package poly.pharmacyproject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.PaymentMethodMapper;
import poly.pharmacyproject.Model.Entity.PaymentMethod;
import poly.pharmacyproject.Model.Response.PaymentMethodResponse;
import poly.pharmacyproject.Repo.PaymentMethodRepo;
import poly.pharmacyproject.Service.PaymentMethodService;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;
    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public List<PaymentMethodResponse> getAllPaymentMethod() {
        List<PaymentMethod> paymentMethods = paymentMethodRepo.findAll();
        return paymentMethods.stream()
                .map(paymentMethodMapper :: convertEnToRes)
                .toList();
    }
}

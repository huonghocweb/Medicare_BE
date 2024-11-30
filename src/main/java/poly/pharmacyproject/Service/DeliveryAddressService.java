package poly.pharmacyproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Request.DeliveryAddressRequest;
import poly.pharmacyproject.Model.Response.DeliveryAddressResponse;

@Service
public interface DeliveryAddressService {
    Page<DeliveryAddressResponse> getAllDeliveryAddressByUserId(Integer userId, Pageable pageable);
    DeliveryAddressResponse getDeliveryAddressById(Integer deliveryAddressId);
    DeliveryAddressResponse createDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest);
    DeliveryAddressResponse updateDeliveryAddress(Integer deliveryAddressId, DeliveryAddressRequest deliveryAddressRequest);
}

package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.PaymentMethod;
import poly.pharmacyproject.Model.Response.PaymentMethodResponse;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethodResponse convertEnToRes(PaymentMethod paymentMethod);
}

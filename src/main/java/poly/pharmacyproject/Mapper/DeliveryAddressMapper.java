package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.DeliveryAddress;
import poly.pharmacyproject.Model.Request.DeliveryAddressRequest;
import poly.pharmacyproject.Model.Response.DeliveryAddressResponse;

@Mapper(componentModel = "spring")
public interface  DeliveryAddressMapper {
    DeliveryAddressResponse convertEnToRes(DeliveryAddress deliveryAddress) ;

    DeliveryAddress convertReqToEn(DeliveryAddressRequest deliveryAddressRequest);

}

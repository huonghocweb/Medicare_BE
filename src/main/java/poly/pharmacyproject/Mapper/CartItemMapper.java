package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.CartItem;
import poly.pharmacyproject.Model.Request.CartItemRequest;
import poly.pharmacyproject.Model.Response.CartItemResponse;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemResponse convertEnToRes(CartItem cartItem );
    CartItem convertReqToEn(CartItemRequest cartItemRequest);
    CartItem convertResToEn(CartItemResponse cartItemResponse);
}

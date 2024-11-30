package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.Cart;
import poly.pharmacyproject.Model.Request.CartRequest;
import poly.pharmacyproject.Model.Response.CartResponse;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    CartResponse convertEnToRes(Cart cart);
    Cart convertReqToEn(CartRequest cartRequest);
    Cart convertResToEn(CartResponse cartResponse);
}

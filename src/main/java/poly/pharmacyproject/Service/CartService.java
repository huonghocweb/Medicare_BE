package poly.pharmacyproject.Service;

import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.CartItemResponse;
import poly.pharmacyproject.Model.Response.CartResponse;

import java.util.Map;

@Service
public interface CartService {
        CartResponse getCartByUserId(Integer userId);
        CartResponse addToCart(Integer userId, Map<Integer , Integer> cartItems );
        CartItemResponse removeCartItem(Integer cartItemId);
        CartResponse addCouponToCart(Integer userId, String code);
        CartResponse removerCouponToCart(Integer userId, String code);
}

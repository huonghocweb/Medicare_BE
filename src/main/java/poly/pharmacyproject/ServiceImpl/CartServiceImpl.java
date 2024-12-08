package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.CartItemMapper;
import poly.pharmacyproject.Mapper.CartMapper;
import poly.pharmacyproject.Mapper.VariationMapper;
import poly.pharmacyproject.Model.Entity.Cart;
import poly.pharmacyproject.Model.Entity.CartItem;
import poly.pharmacyproject.Model.Entity.Coupon;
import poly.pharmacyproject.Model.Entity.Variation;
import poly.pharmacyproject.Model.Response.CartItemResponse;
import poly.pharmacyproject.Model.Response.CartResponse;
import poly.pharmacyproject.Model.Response.VariationResponse;
import poly.pharmacyproject.Repo.*;
import poly.pharmacyproject.Service.CartService;
import poly.pharmacyproject.Service.VariationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private VariationService variationService;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private VariationRepo variationRepo;
    @Autowired
    private VariationMapper variationMapper;
    @Autowired
    private CouponRepo couponRepo;

    @Override
    public CartResponse getCartByUserId(Integer userId) {
        Cart cart = cartRepo.getCartByUserId(userId);
        if(cart == null){
            cart = new Cart();
            cart.setUser(userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("not found User")));
            cart.setTotalPrice(0.0);
            cart.setTotalQuantity(0);
            cart= cartRepo.save(cart);
        }
        return cartMapper.convertEnToRes(cart);
    }

    @Override
    public CartResponse addToCart(Integer userId, Map<Integer , Integer> cartItems ) {
        System.out.println("Add to Cart " + userId);
       CartResponse cartResponse = this.getCartByUserId(userId);
        Cart cart = cartMapper.convertResToEn(cartResponse);
       System.out.println("Cart : " + cartResponse);
        List<CartItem> cartItemList = new ArrayList<>();
        Double totalPrice = 0.0;
        Integer totalQuantity = 0;
      //  List<CartItem> cartItemList = cartItemRepo.getCartItemByCartId(cartResponse.getCartId());
        for(Map.Entry<Integer , Integer> cartItemMap : cartItems.entrySet() ) {
            System.out.println("Food VaID : " + cartItemMap.getKey());
            System.out.println("Quantity : " + cartItemMap.getValue());
            System.out.println("Count");
            CartItem cartItem ;
            cartItem = cartItemRepo.getCartItemByVaId(cartItemMap.getKey());
//            System.out.println("Cart Item : " + cartItem);
            Variation variation = variationRepo.findById(cartItemMap.getKey())
                    .orElseThrow(() -> new EntityNotFoundException("Not found Variation"));
            System.out.println("Variation : " + variationMapper.convertEnToRes(variation));
            Double vaPrice = variation.getPriceHistories().get(0).getPrice();
            if(cartItem == null){
                cartItem = new CartItem();
                cartItem.setQuantity(cartItemMap.getValue());
                cartItem.setPrice(cartItemMap.getValue() *vaPrice );
                cartItem.setVariation(variation);
                cartItem.setCart(cart);
                System.out.println("Cart Item NEW  : " + cartItemMapper.convertEnToRes(cartItem));
            }else {
                cartItem.setQuantity(cartItem.getQuantity() + cartItemMap.getValue());
                cartItem.setPrice(cartItem.getQuantity() * vaPrice);
                System.out.println("Cart Item : " + cartItemMapper.convertEnToRes(cartItem));
            }
            totalPrice += cartItemMap.getValue() * vaPrice;
            totalQuantity += cartItemMap.getValue();
            System.out.println("ToTalPrice " + totalPrice);
            System.out.println("Total Quantity" + totalQuantity);
            if(cartItem.getQuantity() < 1){
                cartItemRepo.delete(cartItem);
            }else {
                cartItemList.add(cartItemRepo.save(cartItem));
            }
        }
        System.out.println("Cart List : " + cartItemList.size());
        cart.setTotalQuantity(cartResponse.getTotalQuantity() + totalQuantity);
        cart.setTotalPrice(cartResponse.getTotalPrice() + totalPrice);
       // cart.setCartItems(cartItemList);
        return cartMapper.convertEnToRes(cartRepo.save(cart));
//        return null;
    }

    @Override
    public CartItemResponse removeCartItem(Integer cartItemId) {
        return null;
    }

    @Override
    public CartResponse addCouponToCart(Integer userId, String code){
        Cart cart = cartRepo.getCartByUserId(userId);
        Coupon coupon = couponRepo.findCouponByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Not found Coupon"));
        cart.setCoupon(coupon);
        System.out.println( "Discount Percent: " + coupon.getDiscountPercent()/100);
        System.out.println("TOtal Price " + cart.getTotalPrice());
        Double discountAmount = ((coupon.getDiscountPercent() / 100) *cart.getTotalPrice());
        System.out.println(discountAmount);
        if (discountAmount > coupon.getMaxDiscountAmount()){
            discountAmount = coupon.getMaxDiscountAmount();
        }
        cart.setDiscountAmount(discountAmount);
        return cartMapper.convertEnToRes(cartRepo.save(cart));
    }

    @Override
    public CartResponse removerCouponToCart(Integer userId, String code){
        Cart cart = cartRepo.getCartByUserId(userId);
        Coupon coupon = couponRepo.findCouponByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Not found Coupon"));
        if(coupon != null){
            cart.setCoupon(null);
            cart.setDiscountAmount(0.0);
        }
        return cartMapper.convertEnToRes(cartRepo.save(cart));
    }
}

package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import poly.pharmacyproject.Service.CartService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartApi {
    @Autowired
    private CartService cartService;

    @GetMapping("/getByUserId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getCartByUserId(
            @PathVariable("userId") Integer userId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Get Cart By UserId");
            result.put("data",cartService.getCartByUserId(userId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addToCart/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> addToCart(
            @PathVariable("userId") Integer userId,
            @RequestPart("cartItems") Map<Integer, Integer> cartItems
    ){
        Map<String,Object> result = new HashMap<>();
        System.out.println(cartItems);
        for(Map.Entry<Integer , Integer> cartItemMap : cartItems.entrySet() ) {
            System.out.println(cartItemMap.getKey());
            System.out.println(cartItemMap.getValue());
        }
        try {
            result.put("success",true);
            result.put("message","Add Variation To Cart");
            result.put("data",cartService.addToCart(userId, cartItems));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/addCouponToCart/{userId}")
    public ResponseEntity<Object> addToCart(
            @PathVariable("userId") Integer userId,
            @RequestParam("code") String code
    ){
        Map<String,Object> result = new HashMap<>();
        System.out.println(code);
        try {
            result.put("success",true);
            result.put("message","Use Coupon To Cart");
            result.put("data",cartService.addCouponToCart(userId, code));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/removeCouponToCart/{userId}")
    public ResponseEntity<Object> removeCouponToCart(
            @PathVariable("userId") Integer userId,
            @RequestParam("code") String code
    ){
        Map<String,Object> result = new HashMap<>();
        System.out.println(code);
        try {
            result.put("success",true);
            result.put("message","Remove Coupon To Cart");
            result.put("data",cartService.removerCouponToCart(userId, code));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}

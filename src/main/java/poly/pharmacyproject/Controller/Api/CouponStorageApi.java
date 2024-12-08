package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Model.Request.CouponStorageRequest;
import poly.pharmacyproject.Service.CouponStorageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/couponStorage")
public class CouponStorageApi {

    @Autowired
    CouponStorageService couponStorageService;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getCouponStorageByUserId(
            @PathVariable("userId") Integer userId,
            @RequestParam("pageCurrent") Integer pageCurrent,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sortOrder") String sortOrder,
            @RequestParam("sortBy") String sortBy
    ){
        Map<String,Object> result = new HashMap<>();
        Sort sort = Sort.by(new Sort.Order(Objects.equals(sortOrder, "asc")? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Pageable pageable = PageRequest.of(pageCurrent, pageSize, sort);
        try {
            result.put("success",true);
            result.put("message","Get CouponStorage By UserName");
            result.put("data",couponStorageService.getCouponStorageByUserId(userId ,pageable));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/{userId}/yourCoupon")
//    public ResponseEntity<Object> getCouponStorageByUserId(
//            @PathVariable("userId") Integer userId
//    ){
//        Map<String,Object> result = new HashMap<>();
//        System.out.println("123555");
//        try {
//            result.put("success",true);
//            result.put("message","Get CouponStorage By UserName");
//            result.put("data",couponStorageService.getAllCouponStorageByUserId(userId));
//        }catch (Exception e){
//            result.put("success",false);
//            result.put("message",e.getMessage());
//            result.put("data", null);
//        }
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/addCouponToStorage/{userId}")
    public ResponseEntity<Object> createCoupon(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "code",required = false) String code
    ){
        System.out.println("Claim Coupon ");
        System.out.println(userId);
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Add Coupon To CouponStorage ");
            result.put("data",couponStorageService.addCouponToCouponStorage(userId, code));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{couponStorageId}")
    public ResponseEntity<Object> deleteCouponStorageById(@PathVariable("couponStorageId") Integer couponStorageId){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Delete CouponStorage By Id ");
            result.put("data",couponStorageService.removeCouponInStorage(couponStorageId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

}

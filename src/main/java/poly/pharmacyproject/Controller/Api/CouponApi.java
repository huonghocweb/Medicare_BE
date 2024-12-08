package poly.pharmacyproject.Controller.Api;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.pharmacyproject.Model.Request.CouponRequest;
import poly.pharmacyproject.Model.Response.CouponResponse;
import poly.pharmacyproject.Service.CloudinaryService;
import poly.pharmacyproject.Service.CouponService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/coupon")
public class CouponApi {
    @Autowired
    CouponService couponService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<Object> getAllCoupon(
            @RequestParam("pageCurrent") Integer pageCurrent,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sortOrder") String sortOrder,
            @RequestParam("sortBy") String sortBy
    ){
        //System.out.println("Get All Coupon" + couponService.getAllCoupon(pageCurrent,pageSize,sortOrder,sortBy));
        Map<String,Object> result = new HashMap<>();
        Sort sort = Sort.by(new Sort.Order(Objects.equals(sortOrder, "asc") ? Sort.Direction.ASC : Sort.Direction.DESC , sortBy));
        Pageable pageable = PageRequest.of(pageCurrent, pageSize, sort);
        try {
            result.put("success",true);
            result.put("message","Get All Coupon");
            result.put("data",couponService.getAllCoupon(pageable));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<Object> getCouponByCouponId(@PathVariable("couponId") Integer couponId){
        Map<String ,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Get Coupon By CouponId");
            result.put("data",couponService.getCouponResponseById(couponId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<Object> createCoupon(
            @RequestPart("couponRequest") CouponRequest couponRequest,
            @RequestPart(value = "files",required = false) MultipartFile[] files) throws IOException {
        Map<String,Object> result = new HashMap<>();
        System.out.println("123 : " + couponRequest);
        if(files != null){
            couponRequest.setImageUrl(cloudinaryService.uploadFile(files, "coupon").get(0));
//           couponRequest.setImageUrl(fileManageUtils.save(folder,files).get(0));
        }else{
            System.out.println("file null");
            couponRequest.setImageUrl(" ");
        }
        try {
            result.put("success",true);
            result.put("message","Create Coupon");
            result.put("data",couponService.createCoupon(couponRequest));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{couponId}")
    public ResponseEntity<Object> updateCoupon(
            @PathVariable("couponId") Integer couponId,
            @RequestPart("couponRequest") CouponRequest couponRequest,
            @RequestPart(value = "files",required = false) MultipartFile[] files
    ) throws IOException {
        Map<String,Object> result = new HashMap<>();

        if(files == null){
            System.out.println("file null");
            CouponResponse couponResponse = couponService.getCouponResponseById(couponId)
                    .orElseThrow(() -> new EntityNotFoundException("Not Found Coupon"));
            couponRequest.setImageUrl(couponResponse.getImageUrl());
        }else{
            couponRequest.setImageUrl(cloudinaryService.uploadFile(files, "coupon").get(0));
        }
        System.out.println(couponRequest);
        try {
            result.put("success",true);
            result.put("message", "Update Coupon");
            result.put("data",couponService.updateCoupon(couponId , couponRequest));
        }catch (Exception e ){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{couponId}")
    public ResponseEntity<Object> removeCoupon(
            @PathVariable("couponId") Integer couponId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message", "Delete Coupon");
            result.put("data",couponService.removeCoupon(couponId));
        }catch (Exception e ){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/checkCoupon/{code}")
    public ResponseEntity<Object> checkCouponByCode(@PathVariable("code") String code){
        Map<String,Object> result = new HashMap<>();
        System.out.println("Check COupon");
        System.out.println(couponService.checkCouponByCode(code));
        try {
            result.put("success",true);
            result.put("message", "Check Coupon By Code");
            result.put("data",couponService.checkCouponByCode(code));
        }catch (Exception e ){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}

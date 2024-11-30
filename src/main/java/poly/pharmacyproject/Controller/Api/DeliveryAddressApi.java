package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Model.Request.DeliveryAddressRequest;
import poly.pharmacyproject.Service.DeliveryAddressService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/deliveryAddress")
public class DeliveryAddressApi {
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("/getAllByUserId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getAllDeliveryAddress(
            @PathVariable("userId") Integer userId,
            @RequestParam("pageCurrent") Integer pageCurrent,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sortOrder") String sortOrder,
            @RequestParam("sortBy") String sortBy
    ){
        Map<String,Object> result = new HashMap<>();
        Sort sort = Sort.by(new Sort.Order(Objects.equals("asc", sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Pageable pageable = PageRequest.of(pageCurrent, pageSize, sort);
        try {
            result.put("success", "true");
            result.put("message", "Get All Delivery Address");
            result.put("data",deliveryAddressService.getAllDeliveryAddressByUserId(userId , pageable));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getById/{deliveryAddressId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getAllDeliveryAddress(
            @PathVariable("deliveryAddressId") Integer deliveryAddressId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Get Delivery Address By Id");
            result.put("data",deliveryAddressService.getDeliveryAddressById(deliveryAddressId ));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> createDeliveryAddress(
            @RequestParam("deliveryAddressRequest") DeliveryAddressRequest deliveryAddressRequest
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Create Delivery Address ");
            result.put("data",deliveryAddressService.createDeliveryAddress(deliveryAddressRequest ));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{deliveryAddressId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> updateDeliveryAddress(
            @PathVariable("deliveryAddressId") Integer deliveryAddressRequestId ,
            @RequestParam("deliveryAddressRequest") DeliveryAddressRequest deliveryAddressRequest
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Update Delivery Address ");
            result.put("data",deliveryAddressService.updateDeliveryAddress(deliveryAddressRequestId,deliveryAddressRequest ));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


}

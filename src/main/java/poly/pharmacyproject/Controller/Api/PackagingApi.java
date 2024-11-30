package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Service.PackagingService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/packaging")
public class PackagingApi {

    @Autowired
    private PackagingService packagingService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getAllPackaging(){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Get All Packaging");
            result.put("data", packagingService.getAllPackaging());
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getById/{packagingId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getPackagingById(
            @PathVariable("packagingId") Integer packagingId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Get Packaging By Id");
            result.put("data", packagingService.getPackagingById(packagingId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByProductId/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getPackagingByProductId(
            @PathVariable("productId") Integer productId
    ){
        Map<String,Object> result = new HashMap<>();
  //      System.out.println("Get By ProductId " +packagingService.getPackagingsByProductId(productId) );
        try {
            result.put("success",true);
            result.put("message","Get Packaging By Product Id");
            result.put("data", packagingService.getPackagingsByProductId(productId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}

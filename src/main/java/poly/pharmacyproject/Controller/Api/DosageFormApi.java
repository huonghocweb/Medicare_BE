package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Service.DosageFormService;
import poly.pharmacyproject.Service.PackagingService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dosageForm")
public class DosageFormApi {

    @Autowired
    private DosageFormService dosageFormService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getAllDosageForm(){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Get All DosageForm");
            result.put("data", dosageFormService.getAllDosageForm());
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getById/{dosageFormId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getDosageFormById(
            @PathVariable("dosageFormId") Integer dosageFormId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Get DosageForm By Id");
            result.put("data", dosageFormService.getDosageFormId(dosageFormId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByProductId/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getDosageFormByProductId(
            @PathVariable("productId") Integer productId
    ){
        Map<String,Object> result = new HashMap<>();
       // System.out.println("getDosageFormByProductId :  " + dosageFormService.getDosageFormsByProductId(productId) );
        try {
            result.put("success",true);
            result.put("message","Get DosageForm By Product Id");
            result.put("data", dosageFormService.getDosageFormsByProductId(productId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}

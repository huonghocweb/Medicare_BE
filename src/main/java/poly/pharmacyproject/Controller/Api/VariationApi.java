package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Service.VariationService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/variation")
public class VariationApi {

    @Autowired
    private VariationService variationService;


    @GetMapping("getById/{variationId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getProductById(
            @PathVariable("variationId") Integer variationId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Get Variation By Id");
            result.put("data",variationService.getVariationById(variationId));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("getByPackagingId/{productId}/{packagingId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getVariationByPackagingId(
            @PathVariable("productId") Integer productId,
            @PathVariable("packagingId") Integer packagingId
    ){
        Map<String,Object> result = new HashMap<>();
        System.out.println("Get By Packaging Id : " +variationService.getVariationByPackagingId(productId,packagingId) );
        try {
            result.put("success", "true");
            result.put("message", "Get Variation By PackagingId");
            result.put("data",variationService.getVariationByPackagingId(productId,packagingId));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("getByDosageFormId/{productId}/{dosageFormId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getVariationByDosageFormId(
            @PathVariable("productId") Integer productId,
            @PathVariable("dosageFormId") Integer dosageFormId
    ){
        Map<String,Object> result = new HashMap<>();
        //System.out.println("Get By DosageForm Id : " + variationService.getVariationByDosageForm(productId,dosageFormId));
        try {
            result.put("success", "true");
            result.put("message", "Get Variation By PackagingId");
            result.put("data",variationService.getVariationByDosageForm(productId,dosageFormId));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("getByDosageFormIdAndPackagingId/{productId}/{dosageFormId}/{packagingId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getVariationByDosageFormIdAndPackagingId(
            @PathVariable("productId") Integer productId,
            @PathVariable("dosageFormId") Integer dosageFormId,
            @PathVariable("packagingId") Integer packagingId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "get Variation By DosageFormId And PackagingId");
            result.put("data",variationService.getVariationByPackagingIdAndDosageFormId(productId,packagingId, dosageFormId));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

}

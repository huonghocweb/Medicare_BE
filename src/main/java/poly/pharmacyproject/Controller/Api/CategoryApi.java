package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Service.CategoryService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category")
public class CategoryApi {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getAllCategory(
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Get All Category");
            result.put("data",categoryService.getAllCategory());
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("getById/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' , 'USER')")
    public ResponseEntity<Object> getProductById(
            @PathVariable("categoryId") Integer categoryId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Get Category By Id");
            result.put("data",categoryService.getCategoryById(categoryId));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}

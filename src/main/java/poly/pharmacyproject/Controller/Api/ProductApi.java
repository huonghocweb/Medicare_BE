package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.pharmacyproject.Model.Entity.Product;
import poly.pharmacyproject.Model.Entity.User;
import poly.pharmacyproject.Model.Request.ProductRequest;
import poly.pharmacyproject.Model.Request.UserRequest;
import poly.pharmacyproject.Model.Response.ProductResponse;
import poly.pharmacyproject.Service.CloudinaryService;
import poly.pharmacyproject.Service.ProductService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductApi {

    @Autowired
    private ProductService productService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getAllProduct(
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
            result.put("message", "Get All User");
            result.put("data",productService.getAllProduct(pageable));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("getById/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getProductById(
            @PathVariable("productId") Integer productId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success", "true");
            result.put("message", "Get Product By Id ");
            result.put("data",productService.getProductById(productId));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createUser(
            @RequestPart(value = "productRequest",required = false) ProductRequest productRequest,
            @RequestPart(value = "files",required = false) MultipartFile[] files
    ) throws IOException {
        Map<String,Object> result = new HashMap<>();
        if(files != null){
            productRequest.setImageUrl(cloudinaryService.uploadFile(files,"product").get(0));
        }else {
            productRequest.setImageUrl("");
        }
        try {
            result.put("success", "true");
            result.put("message", "Create New Product");
            result.put("data",productService.createProduct(productRequest));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateUser(
            @PathVariable("productId") Integer productId ,
            @RequestPart(value = "productRequest" , required = false) ProductRequest productRequest,
            @RequestPart(value = "files",required = false) MultipartFile [] files
    ) throws IOException {
        Map<String,Object> result = new HashMap<>();
        if(files != null){
            productRequest.setImageUrl(cloudinaryService.uploadFile(files, "product").get(0));
        }else {
            ProductResponse productResponse = productService.getProductById(productId);
            productRequest.setImageUrl(productResponse.getImageUrl());
        }
        try {
            result.put("success", "true");
            result.put("message", "Update Product");
            result.put("data",productService.updateProduct(productId, productRequest));
        }catch (Exception e){
            result.put("success", "false");
            result.put("message", e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }


}

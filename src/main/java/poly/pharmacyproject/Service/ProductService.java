package poly.pharmacyproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Request.ProductRequest;
import poly.pharmacyproject.Model.Response.ProductResponse;

@Service
public interface ProductService {
    Page<ProductResponse> getAllProduct(Pageable pageable);
    ProductResponse getProductById(Integer productId);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(Integer productId, ProductRequest productRequest);
    ProductResponse deleteProduct(Integer productId);
}

package poly.pharmacyproject.Mapper;

import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import poly.pharmacyproject.Model.Entity.Product;
import poly.pharmacyproject.Model.Request.ProductRequest;
import poly.pharmacyproject.Model.Response.ProductResponse;
import poly.pharmacyproject.Repo.CategoryRepo;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface  ProductMapper {

    ProductResponse convertEnToRes(Product product);

    Product convertReqToEn(ProductRequest productRequest);

//    ProductResponse convertEnToRes(Product product){
//        return ProductResponse.builder()
//                .productId(product.getProductId())
//                .productName(product.getProductName())
//                .description(product.getDescription())
//                .imageUrl(product.getImageUrl())
//                .createAt(product.getCreateAt())
//                .updateAt(product.getUpdateAt())
//                .category(product.getCategory() != null ? categoryMapper.convertEnToRes(product.getCategory()) :null)
//                .build();
//    }
//
//    Product convertReqToEn(ProductRequest productRequest){
//        return Product.builder()
//                .productName(productRequest.getProductName())
//                .imageUrl(productRequest.getImageUrl())
//                .description(productRequest.getDescription())
//                .createAt(productRequest.getCreateAt())
//                .updateAt(LocalDateTime.now())
//                .category(productRequest.getCategoryId() != null ?
//                        categoryRepo.findById(productRequest.getCategoryId())
//                                .orElseThrow(() -> new EntityNotFoundException("Not found Category")) : null)
//                .build();
//    }
}

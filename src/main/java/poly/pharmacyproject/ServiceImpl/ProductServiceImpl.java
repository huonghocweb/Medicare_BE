package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.ProductMapper;
import poly.pharmacyproject.Model.Entity.Product;
import poly.pharmacyproject.Model.Request.ProductRequest;
import poly.pharmacyproject.Model.Response.ProductResponse;
import poly.pharmacyproject.Repo.CategoryRepo;
import poly.pharmacyproject.Repo.ProductRepo;
import poly.pharmacyproject.Service.ProductService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Page<ProductResponse> getAllProduct(Pageable pageable) {
        Page<Product> productPage = productRepo.findAll(pageable);
        if  (pageable.getPageNumber()>= (productPage.getTotalPages() -1) ){
            pageable = PageRequest.of(productPage.getTotalPages()-1, pageable.getPageSize(), pageable.getSort());
        }
        List<ProductResponse> productResponses = productPage.getContent().stream()
                .map(productMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return new PageImpl<>(productResponses,pageable,productPage.getTotalElements());
    }

    @Override
    public ProductResponse getProductById(Integer productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Not found Product"));
        return productMapper.convertEnToRes(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.convertReqToEn(productRequest);
        product.setCategory(productRequest.getCategoryId() != null ?
                categoryRepo.findById(productRequest.getCategoryId())
                        .orElseThrow(() -> new EntityNotFoundException("not found Category")) : null);
        Product productCreated = productRepo.save(product);
        return productMapper.convertEnToRes(productCreated);
    }

    @Override
    public ProductResponse updateProduct(Integer productId, ProductRequest productRequest) {
        return productRepo.findById(productId).map(productExists -> {
            Product product = productMapper.convertReqToEn(productRequest);
            product.setProductId(productExists.getProductId());
            product.setUpdateAt(LocalDate.now());
            product.setCategory(productRequest.getCategoryId() != null ?
                    categoryRepo.findById(productRequest.getCategoryId())
                            .orElseThrow(() -> new EntityNotFoundException("not found Category")) : null);
            Product productUpdated = productRepo.save(product);
            return productMapper.convertEnToRes(productUpdated);
        }).orElseThrow(() -> new EntityNotFoundException("not found Product"));
    }

    @Override
    public ProductResponse deleteProduct(Integer productId) {
//        return productRepo.findById(productId).map(productExists -> {
//            productExists.set
//            Product productUpdated = productRepo.save(product);
//            return productMapper.convertEnToRes(productUpdated);
//        }).orElseThrow(() -> new EntityNotFoundException("not found Product"));
        return null;
    }
}

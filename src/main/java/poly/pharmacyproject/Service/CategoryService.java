package poly.pharmacyproject.Service;

import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.CategoryResponse;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryResponse> getAllCategory();
    CategoryResponse getCategoryById(Integer categoryId);
}

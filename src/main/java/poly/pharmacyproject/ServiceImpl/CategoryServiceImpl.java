package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.CategoryMapper;
import poly.pharmacyproject.Model.Entity.Category;
import poly.pharmacyproject.Model.Response.CategoryResponse;
import poly.pharmacyproject.Repo.CategoryRepo;
import poly.pharmacyproject.Service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(categoryMapper :: convertEnToRes)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("not found Category"));
        return categoryMapper.convertEnToRes(category);
    }
}

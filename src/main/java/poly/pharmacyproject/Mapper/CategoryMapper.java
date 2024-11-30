package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;
import poly.pharmacyproject.Model.Entity.Category;
import poly.pharmacyproject.Model.Response.CategoryResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse convertEnToRes(Category category);
}

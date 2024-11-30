package poly.pharmacyproject.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.pharmacyproject.Model.Entity.Product;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

        private Integer categoryId;
        private String categoryName;
    }

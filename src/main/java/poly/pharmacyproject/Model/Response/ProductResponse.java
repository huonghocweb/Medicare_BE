package poly.pharmacyproject.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.pharmacyproject.Model.Entity.Category;
import poly.pharmacyproject.Model.Entity.WishList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
        private Integer productId;
        private String productName;
        private String imageUrl;
        private Double basePrice;
        private String description;
        private LocalDate createAt;
        private LocalDateTime updateAt;
        private CategoryResponse category;
       // private List<WishList> wishLists;
    }


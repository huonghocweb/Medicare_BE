package poly.pharmacyproject.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
        private String productName;
        private String imageUrl;
        private Double basePrice;
        private String description;
        private LocalDate createAt;
        private Integer categoryId;
       // private List<WishList> wishLists;
    }


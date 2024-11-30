package poly.pharmacyproject.Model.Response;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponResponse {
    private Integer couponId;
    private String code;
    private String description;
    private Double discountPercent;
    private Double maxDiscountAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer useLimit;
    private Integer usedCount;
    private String imageUrl;

}

package poly.pharmacyproject.Model.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.pharmacyproject.Model.Entity.Coupon;
import poly.pharmacyproject.Model.Entity.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponStorageResponse {
    private Integer couponStorageId;
    private Boolean status;
    private UserResponse user;
    private CouponResponse coupon;
}

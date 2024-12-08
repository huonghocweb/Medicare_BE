package poly.pharmacyproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Request.CouponRequest;
import poly.pharmacyproject.Model.Response.CouponResponse;

import java.util.Optional;

@Service
public interface CouponService {
    Page<CouponResponse> getAllCoupon(Pageable pageable);
    Optional<CouponResponse> getCouponResponseById(Integer couponId);
    CouponResponse createCoupon(CouponRequest couponRequest);
    Optional<CouponResponse> updateCoupon(Integer couponId, CouponRequest couponRequest);
    CouponResponse removeCoupon(Integer couponId);
    Optional<Object> checkCouponByCode(String code);
}

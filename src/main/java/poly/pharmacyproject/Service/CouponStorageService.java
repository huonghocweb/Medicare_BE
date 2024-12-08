package poly.pharmacyproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Request.CouponStorageRequest;
import poly.pharmacyproject.Model.Response.CouponStorageResponse;

import java.util.List;
import java.util.Optional;

@Service
public interface CouponStorageService {
    List<CouponStorageResponse> getAllCouponStorageByUserId(Integer userId );
    Page<CouponStorageResponse> getCouponStorageByUserId(Integer userId, Pageable pageable);
    Optional<CouponStorageResponse> getCouponStorageByCouponStorageId(Integer couponStorageId);
    CouponStorageResponse addCouponToCouponStorage(Integer userId, String code);
    CouponStorageResponse removeCouponInStorage(Integer couponStorageId);
}

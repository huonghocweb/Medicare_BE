package poly.pharmacyproject.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.CouponStorage;

import java.util.List;

public interface CouponStorageRepo extends JpaRepository<CouponStorage, Integer> {
    @Query("SELECT cps FROM CouponStorage  cps JOIN cps.user u WHERE u.userId = :userId")
    Page<CouponStorage> getCouponStorageByUserId(@Param("userId") Integer userId , Pageable pageable);
    @Query("SELECT cps FROM CouponStorage  cps JOIN cps.user u WHERE u.userId = :userId")
    List<CouponStorage> getAllCouponStorageByUserId(@Param("userId") Integer userId );
    @Query("SELECT cps FROM CouponStorage cps JOIN cps.user u JOIN cps.coupon cp " +
            " WHERE u.userId= :userId AND cp.couponId=:couponId ")
    CouponStorage getCouponStorageByUserIdAndCouponId(@Param("userId") Integer userId,
                                                        @Param("couponId") Integer couponId);
}

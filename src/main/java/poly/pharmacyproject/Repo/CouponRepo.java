package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.Coupon;
import poly.pharmacyproject.Model.Entity.User;

import java.util.List;
import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    @Query("SELECT c FROM Coupon  c WHERE " +
            " c.code = :code ")
    Optional<Coupon> findCouponByCode(@Param("code") String code);
}

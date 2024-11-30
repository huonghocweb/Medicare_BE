package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.Coupon;
import poly.pharmacyproject.Model.Entity.User;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
}

package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.PaymentMethod;
import poly.pharmacyproject.Model.Entity.User;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Integer> {
}

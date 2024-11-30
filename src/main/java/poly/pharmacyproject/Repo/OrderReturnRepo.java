package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.OrderReturn;
import poly.pharmacyproject.Model.Entity.User;

public interface OrderReturnRepo extends JpaRepository<OrderReturn, Integer> {
}

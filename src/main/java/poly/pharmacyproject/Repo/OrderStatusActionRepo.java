package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.OrderStatusAction;

public interface OrderStatusActionRepo extends JpaRepository<OrderStatusAction, Integer> {
}

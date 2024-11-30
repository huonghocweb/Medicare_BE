package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.OrderStatus;

public interface OrderStatusRepo extends JpaRepository<OrderStatus, Integer> {
}

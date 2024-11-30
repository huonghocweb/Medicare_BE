package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.StockMovement;
import poly.pharmacyproject.Model.Entity.User;

public interface StockMovementRepo extends JpaRepository<StockMovement, Integer> {
}

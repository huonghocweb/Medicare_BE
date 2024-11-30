package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.PriceHistory;
import poly.pharmacyproject.Model.Entity.User;

public interface PriceHistoryRepo extends JpaRepository<PriceHistory, Integer> {
}

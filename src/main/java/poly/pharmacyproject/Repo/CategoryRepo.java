package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.Category;
import poly.pharmacyproject.Model.Entity.User;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}

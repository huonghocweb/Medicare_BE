package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.ImageDetails;
import poly.pharmacyproject.Model.Entity.User;

public interface ImageDetailsRepo extends JpaRepository<ImageDetails, Integer> {
}

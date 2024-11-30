package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.Packaging;
import poly.pharmacyproject.Model.Entity.User;

import java.util.List;

public interface PackagingRepo extends JpaRepository<Packaging, Integer> {

    @Query("SELECT pa FROM Product p JOIN p.variations va JOIN va.packaging pa " +
            " WHERE p.productId = :productId")
    List<Packaging> getPackagingsdByProductId(@Param("productId") Integer productId);

}

package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.DosageForm;
import poly.pharmacyproject.Model.Entity.User;

import java.util.List;

public interface DosageFormRepo extends JpaRepository<DosageForm, Integer> {

    @Query("SELECT do FROM Product p JOIN p.variations va JOIN va.dosageForm do " +
            " WHERE p.productId = :productId")
    List<DosageForm> getDosageFormsByProductId(@Param("productId") Integer productId);
}

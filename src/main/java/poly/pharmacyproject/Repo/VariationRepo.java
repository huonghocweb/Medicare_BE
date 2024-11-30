package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.CartItem;
import poly.pharmacyproject.Model.Entity.User;
import poly.pharmacyproject.Model.Entity.Variation;

import java.util.List;

public interface VariationRepo extends JpaRepository<Variation, Integer> {

    @Query("SELECT va FROM Variation va JOIN va.packaging packa JOIN va.product p " +
            " WHERE packa = :packagingId AND p.productId= :productId")
    List<Variation> getVariationByPackagingId(
            @Param("packagingId") Integer packagingId,
            @Param("productId") Integer productId
    );

    @Query("SELECT va FROM Variation va JOIN va.dosageForm do JOIN va.product p " +
            " WHERE do.dosageFormId = :dosageFormId AND p.productId = :productId")
    List<Variation> getVariationByDosageFormId(
            @Param("dosageFormId") Integer dosageFormId,
            @Param("productId") Integer productId);

    @Query("SELECT va FROM Variation va JOIN va.dosageForm do JOIN va.packaging packa JOIN va.product p " +
            " WHERE do.dosageFormId = :dosageFormId AND packa.packageId = :packagingId " +
            " AND p.productId = :productId ")
    Variation getVariationByPackagingIdAndDosageFormId(
            @Param("dosageFormId") Integer dosageFormId ,
            @Param("packagingId") Integer packagingId,
            @Param("productId") Integer productId);



}

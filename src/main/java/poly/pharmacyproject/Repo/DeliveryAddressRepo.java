package poly.pharmacyproject.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.DeliveryAddress;

public interface DeliveryAddressRepo extends JpaRepository<DeliveryAddress , Integer> {
    @Query("SELECT de FROM DeliveryAddress de JOIN de.user u " +
            " WHERE u.userId = :userId ")
    Page<DeliveryAddress> getDeliveryAddressByUserId(Pageable pageable, @Param("userId") Integer userId);
}

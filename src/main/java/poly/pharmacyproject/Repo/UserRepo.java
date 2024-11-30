package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User , Integer> {
    Optional<User> findUserByUserName(String userName);

    @Query("SELECT u FROM User u " +
            " WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
}

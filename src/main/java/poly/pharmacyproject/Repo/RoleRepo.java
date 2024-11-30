package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.pharmacyproject.Model.Entity.Role;
import poly.pharmacyproject.Model.Entity.User;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
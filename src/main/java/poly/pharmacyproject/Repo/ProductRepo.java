package poly.pharmacyproject.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.pharmacyproject.Model.Entity.DosageForm;
import poly.pharmacyproject.Model.Entity.Packaging;
import poly.pharmacyproject.Model.Entity.Product;
import poly.pharmacyproject.Model.Entity.User;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}

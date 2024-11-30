package poly.pharmacyproject.Service;

import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.RoleResponse;

import java.util.List;

@Service
public interface RoleService {

    List<RoleResponse> getAllRole ();
    RoleResponse getRoleById(Integer roleId);
}

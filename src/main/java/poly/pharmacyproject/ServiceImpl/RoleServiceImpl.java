package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.RoleMapper;
import poly.pharmacyproject.Model.Entity.Role;
import poly.pharmacyproject.Model.Response.RoleResponse;
import poly.pharmacyproject.Repo.RoleRepo;
import poly.pharmacyproject.Service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<RoleResponse> getAllRole() {
        List<Role> roles = roleRepo.findAll();
        return roles.stream()
                .map(roleMapper :: convertEnToRes)
                .toList();
    }

    @Override
    public RoleResponse getRoleById(Integer roleId) {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("not found Role"));
        return roleMapper.convertEnToRes(role);
    }
}

package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.Role;
import poly.pharmacyproject.Model.Response.RoleResponse;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse convertEnToRes(Role role);
}

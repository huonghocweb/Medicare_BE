package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.Packaging;
import poly.pharmacyproject.Model.Response.PackagingResponse;

@Mapper(componentModel = "spring")
public interface PackagingMapper {
    PackagingResponse convertEnToRes(Packaging packaging);
}

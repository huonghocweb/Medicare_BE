package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.Attribute;
import poly.pharmacyproject.Model.Response.AttributeResponse;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    AttributeResponse convertEnToRes(Attribute attribute);
}

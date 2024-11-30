package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.DosageForm;
import poly.pharmacyproject.Model.Response.DosageFormResponse;

@Mapper(componentModel = "spring")
public interface DosageFormMapper {
    DosageFormResponse convertEnToRes(DosageForm dosageForm);
}

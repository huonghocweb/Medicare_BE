package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.StockMovement;
import poly.pharmacyproject.Model.Response.StockMovementResponse;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {
    StockMovementResponse convertEnToRes(StockMovement stockMovement);
}

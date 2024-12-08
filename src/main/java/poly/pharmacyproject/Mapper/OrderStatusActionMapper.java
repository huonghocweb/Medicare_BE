package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.OrderStatusAction;
import poly.pharmacyproject.Model.Response.OrderStatusActionResponse;

@Mapper(componentModel = "spring")
public interface OrderStatusActionMapper {
    OrderStatusActionResponse convertEnToRes(OrderStatusAction orderStatusAction);
}

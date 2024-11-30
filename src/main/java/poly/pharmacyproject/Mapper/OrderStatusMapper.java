package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.OrderStatus;
import poly.pharmacyproject.Model.Response.OrderStatusResponse;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
     OrderStatusResponse convertEnToRes(OrderStatus  orderStatus);
}

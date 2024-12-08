package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import poly.pharmacyproject.Model.Entity.OrderStatus;
import poly.pharmacyproject.Model.Response.OrderStatusResponse;

@Mapper(componentModel = "spring" , uses = OrderStatusActionMapper.class)
public interface OrderStatusMapper {
     OrderStatusResponse convertEnToRes(OrderStatus  orderStatus);
}

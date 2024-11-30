package poly.pharmacyproject.Mapper;

import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import poly.pharmacyproject.Model.Entity.OrderDetails;
import poly.pharmacyproject.Model.Request.OrderDetailsRequest;
import poly.pharmacyproject.Model.Response.OrderDetailsResponse;
import poly.pharmacyproject.Repo.OrderRepo;
import poly.pharmacyproject.Repo.VariationRepo;

@Mapper(componentModel = "spring")
public interface  OrderDetailsMapper {

     OrderDetailsResponse convertEnToRes(OrderDetails orderDetails);
    //public OrderDetails convertReqToEn(OrderDetailsRequest orderDetailsRequest);
     OrderDetails convertReqToEn(OrderDetailsRequest orderDetailsRequest);
}

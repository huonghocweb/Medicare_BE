package poly.pharmacyproject.Mapper;

import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import poly.pharmacyproject.Model.Entity.OrderReturn;
import poly.pharmacyproject.Model.Request.OrderReturnRequest;
import poly.pharmacyproject.Model.Response.OrderReturnResponse;
import poly.pharmacyproject.Repo.OrderRepo;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface  OrderReturnMapper {

    OrderReturnResponse convertEnToRes(OrderReturn orderReturn);

    OrderReturn convertReqToEn(OrderReturnRequest orderReturnRequest);

//    public OrderReturnResponse convertEnToRes(OrderReturn orderReturn){
//        return OrderReturnResponse.builder()
//                .orderReturnId(orderReturn.getOrderReturnId())
//                .reason(orderReturn.getReason())
//                .returnDateTime(orderReturn.getReturnDateTime())
//                .status(orderReturn.getStatus())
//                 .order(orderMapper.convertEnToRes(orderReturn.getOrder()))
//                .build();
//    }
//
//    public OrderReturn convertReqToEn(OrderReturnRequest orderReturnRequest){
//        return OrderReturn.builder()
//                .reason(orderReturnRequest.getReason())
//                .status(orderReturnRequest.getStatus())
//                .returnDateTime(LocalDateTime.now())
//                .order(orderRepo.findById(orderReturnRequest.getOrderId())
//                        .orElseThrow(() -> new EntityNotFoundException("Order Not found")))
//                .foodVariations(foodVariationsDao.findById(orderReturnRequest.getFoodVaId())
//                        .orElseThrow(()-> new EntityNotFoundException("ProductVa Not found")))
//                .build();
//    }
//
//    public OrderReturnRequest convertResToReq(OrderReturnResponse orderReturnResponse){
//        return OrderReturnRequest.builder()
//                .status(orderReturnResponse.getStatus())
//                .reason(orderReturnResponse.getReason())
//                .orderId(orderReturnResponse.getOrder().getOrderId())
//                .foodVaId(orderReturnResponse.getFoodVariation().getFoodVariationId())
//                .build();
//    }
}
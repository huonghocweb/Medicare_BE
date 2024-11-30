package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.OrderDetailsMapper;
import poly.pharmacyproject.Model.Entity.OrderDetails;
import poly.pharmacyproject.Model.Request.OrderDetailsRequest;
import poly.pharmacyproject.Model.Response.OrderDetailsResponse;
import poly.pharmacyproject.Repo.OrderDetailsRepo;
import poly.pharmacyproject.Repo.OrderRepo;
import poly.pharmacyproject.Repo.VariationRepo;
import poly.pharmacyproject.Service.OrderDetailsService;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private VariationRepo variationRepo;

    @Override
    public Page<OrderDetailsResponse> getAllOrderDetails(Pageable pageable) {

        return null;
    }

    @Override
    public List<OrderDetailsResponse> getOrderDetailsByOrderId(Integer orderId) {
        List<OrderDetails> orderDetailsPage = orderDetailsRepo.getOrderDetailsByOrderId(orderId);
        return orderDetailsPage.stream()
                .map(orderDetailsMapper :: convertEnToRes)
                .toList();
    }

    @Override
    public OrderDetailsResponse createOrderDetails(OrderDetailsRequest orderDetailsRequest) {
        OrderDetails orderDetails = orderDetailsMapper.convertReqToEn(orderDetailsRequest);
        orderDetails.setOrder(orderDetailsRequest.getOrderId()!= null ?
                orderRepo.findById(orderDetailsRequest.getOrderId())
                        .orElseThrow(() -> new EntityNotFoundException("Not found Order")): null);
        orderDetails.setVariation( orderDetailsRequest.getVariationId() != null ? variationRepo.findById(orderDetailsRequest.getVariationId())
                .orElseThrow(() -> new EntityNotFoundException("Not found Variation")) : null);
        OrderDetails orderDetailsCreated = orderDetailsRepo.save(orderDetails);
        return orderDetailsMapper.convertEnToRes(orderDetailsCreated);
    }

    @Override
    public OrderDetailsResponse updateOrderDetails(Integer orderDetailsId, OrderDetailsRequest orderDetailsRequest) {
        return orderDetailsRepo.findById(orderDetailsId).map(orderDetailsExists -> {
            OrderDetails orderDetails = orderDetailsMapper.convertReqToEn(orderDetailsRequest);
            orderDetails.setOrderDetailsId(orderDetailsExists.getOrderDetailsId());
            orderDetails.setOrder(orderDetailsRequest.getOrderId()!= null ?
                    orderRepo.findById(orderDetailsRequest.getOrderId())
                            .orElseThrow(() -> new EntityNotFoundException("Not found Order")): null);
            orderDetails.setVariation( orderDetailsRequest.getVariationId() != null ? variationRepo.findById(orderDetailsRequest.getVariationId())
                    .orElseThrow(() -> new EntityNotFoundException("Not found Variation")) : null);
            OrderDetails orderDetailsUpdated = orderDetailsRepo.save(orderDetails);
            return orderDetailsMapper.convertEnToRes(orderDetailsUpdated);
        }).orElseThrow(() -> new EntityNotFoundException("Not found Order Details"));
    }
}

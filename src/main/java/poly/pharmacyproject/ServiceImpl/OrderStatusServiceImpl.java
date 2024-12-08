package poly.pharmacyproject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.OrderStatusActionMapper;
import poly.pharmacyproject.Mapper.OrderStatusMapper;
import poly.pharmacyproject.Model.Entity.OrderStatus;
import poly.pharmacyproject.Model.Response.OrderStatusResponse;
import poly.pharmacyproject.Repo.OrderStatusActionRepo;
import poly.pharmacyproject.Repo.OrderStatusRepo;
import poly.pharmacyproject.Service.OrderStatusService;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusRepo orderStatusRepo;
    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderStatusResponse> getAllOrderStatus() {
        List<OrderStatus> orderStatuses = orderStatusRepo.findAll();
        return orderStatuses.stream()
                .map(orderStatusMapper :: convertEnToRes)
                .toList();
    }
}

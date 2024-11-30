package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.DeliveryAddressMapper;
import poly.pharmacyproject.Model.Entity.DeliveryAddress;
import poly.pharmacyproject.Model.Request.DeliveryAddressRequest;
import poly.pharmacyproject.Model.Response.DeliveryAddressResponse;
import poly.pharmacyproject.Repo.DeliveryAddressRepo;
import poly.pharmacyproject.Repo.UserRepo;
import poly.pharmacyproject.Service.DeliveryAddressService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;
    @Autowired
    private DeliveryAddressRepo deliveryAddressRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Page<DeliveryAddressResponse> getAllDeliveryAddressByUserId(Integer userId, Pageable pageable) {
        Page<DeliveryAddress> deliveryAddressPage = deliveryAddressRepo.getDeliveryAddressByUserId(pageable,userId);
        List<DeliveryAddressResponse> deliveryAddressResponses = deliveryAddressPage.getContent().stream()
                .map(deliveryAddressMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return new PageImpl<>(deliveryAddressResponses, pageable, deliveryAddressPage.getTotalElements());
    }

    @Override
    public DeliveryAddressResponse getDeliveryAddressById(Integer deliveryAddressId) {
        DeliveryAddress deliveryAddress = deliveryAddressRepo.findById(deliveryAddressId)
                .orElseThrow(() -> new EntityNotFoundException("not found DeliveryAddress"));
        return deliveryAddressMapper.convertEnToRes(deliveryAddress);
    }

    @Override
    public DeliveryAddressResponse createDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest) {
        DeliveryAddress deliveryAddress = deliveryAddressMapper.convertReqToEn(deliveryAddressRequest);
        deliveryAddress.setUser(userRepo.findById(deliveryAddressRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("not found Delivery Address")));
        DeliveryAddress deliveryAddressCreated = deliveryAddressRepo.save(deliveryAddress);
        return deliveryAddressMapper.convertEnToRes(deliveryAddressCreated);
    }

    @Override
    public DeliveryAddressResponse updateDeliveryAddress(Integer deliveryAddressId, DeliveryAddressRequest deliveryAddressRequest) {
        return deliveryAddressRepo.findById(deliveryAddressId).map(deliveryAddressExists -> {
            DeliveryAddress deliveryAddress = deliveryAddressMapper.convertReqToEn(deliveryAddressRequest);
            deliveryAddress.setDeliveryAddressId(deliveryAddressExists.getDeliveryAddressId());
            deliveryAddress.setUser(userRepo.findById(deliveryAddressRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("not found User")));
            DeliveryAddress deliveryAddressUpdated= deliveryAddressRepo.save(deliveryAddress);
            return deliveryAddressMapper.convertEnToRes(deliveryAddressUpdated);
        }).orElseThrow(() -> new EntityNotFoundException("not found Delivery Address"));
    }
}

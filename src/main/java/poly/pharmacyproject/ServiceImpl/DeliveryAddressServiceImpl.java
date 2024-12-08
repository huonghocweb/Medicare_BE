package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.DeliveryAddressMapper;
import poly.pharmacyproject.Model.Entity.DeliveryAddress;
import poly.pharmacyproject.Model.Request.DeliveryAddressRequest;
import poly.pharmacyproject.Model.Response.DeliveryAddressResponse;
import poly.pharmacyproject.Repo.DeliveryAddressRepo;
import poly.pharmacyproject.Repo.UserRepo;
import poly.pharmacyproject.Service.DeliveryAddressService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    @Autowired
    private DeliveryAddressRepo deliveryAddressRepo;
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;
    @Autowired
    private UserRepo userRepo;


    @Override
    public Page<DeliveryAddressResponse> getAllDeliveryAddress(Pageable pageable) {
        Page<DeliveryAddress> deliveryAddressPage = deliveryAddressRepo.findAll(pageable);
        List<DeliveryAddressResponse> deliveryAddressResponses = deliveryAddressPage.getContent().stream()
                .map(deliveryAddressMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return new PageImpl<>(deliveryAddressResponses,pageable,deliveryAddressPage.getTotalElements());
    }


    @Override
    public Page<DeliveryAddressResponse> getDeliveryAddressByUserId(Integer userId, Pageable pageable) {
        Page<DeliveryAddress> deliveryAddressePage = deliveryAddressRepo.getDeliveryAddressByUserId(  pageable , userId);
        List<DeliveryAddressResponse> deliveryAddressResponses = deliveryAddressePage.getContent().stream()
                .map(deliveryAddressMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return new PageImpl<>(deliveryAddressResponses, pageable, deliveryAddressePage.getTotalElements());
    }

    @Override
    public Optional<DeliveryAddressResponse> getDeliveryAddressById(Integer deliveryAddressId) {
        DeliveryAddress deliveryAddress = deliveryAddressRepo.findById(deliveryAddressId)
                .orElseThrow(() -> new EntityNotFoundException("Not found DeliveryAddress"));
        return Optional.of(deliveryAddressMapper.convertEnToRes(deliveryAddress));
    }

    @Override
    public DeliveryAddressResponse createDeliveryAddress(DeliveryAddressRequest deliveryAddressRequest) {
        DeliveryAddress deliveryAddress = deliveryAddressMapper.convertReqToEn(deliveryAddressRequest);
        deliveryAddress.setUser(userRepo.findById(deliveryAddressRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Not found User")));
        deliveryAddress.setStatus(true);
        DeliveryAddress deliveryAddressCreated = deliveryAddressRepo.save(deliveryAddress);
        return deliveryAddressMapper.convertEnToRes(deliveryAddressCreated);
    }

    @Override
    public Optional<DeliveryAddressResponse> updateDeliveryAddress(Integer deliveryAddressId, DeliveryAddressRequest deliveryAddressRequest) {
        return Optional.of(deliveryAddressRepo.findById(deliveryAddressId).map(deliveryAddressExists -> {
            DeliveryAddress deliveryAddress = deliveryAddressMapper.convertReqToEn(deliveryAddressRequest);
            deliveryAddress.setUser(userRepo.findById(deliveryAddressRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("not found User")));
            deliveryAddress.setDeliveryAddressId(deliveryAddressExists.getDeliveryAddressId());
            DeliveryAddress deliveryAddressUpdated = deliveryAddressRepo.save(deliveryAddress);
            return deliveryAddressMapper.convertEnToRes(deliveryAddressUpdated);
        }).orElseThrow(() -> new EntityNotFoundException("Not found DeliveryAddress")));
    }

    @Override
    public Optional<DeliveryAddressResponse> removerDeliveryAddress(Integer deliveryAddressId) {
        return Optional.of(deliveryAddressRepo.findById(deliveryAddressId).map(deliveryAddressExists -> {
            deliveryAddressExists.setStatus(false);
            DeliveryAddress deliveryAddressUpdated = deliveryAddressRepo.save(deliveryAddressExists);
            return deliveryAddressMapper.convertEnToRes(deliveryAddressUpdated);
        }).orElseThrow(() -> new EntityNotFoundException("Not found DeliveryAddress")));
    }
}

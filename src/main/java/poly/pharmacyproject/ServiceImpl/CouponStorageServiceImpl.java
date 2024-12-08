package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.CouponStorageMapper;
import poly.pharmacyproject.Model.Entity.Coupon;
import poly.pharmacyproject.Model.Entity.CouponStorage;
import poly.pharmacyproject.Model.Entity.User;
import poly.pharmacyproject.Model.Request.CouponStorageRequest;
import poly.pharmacyproject.Model.Response.CouponStorageResponse;
import poly.pharmacyproject.Repo.CouponRepo;
import poly.pharmacyproject.Repo.CouponStorageRepo;
import poly.pharmacyproject.Repo.UserRepo;
import poly.pharmacyproject.Service.CouponStorageService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponStorageServiceImpl implements CouponStorageService {
    @Autowired
    private CouponStorageRepo couponStorageRepo;
    @Autowired
    private CouponStorageMapper couponStorageMapper;
    @Autowired
    private CouponRepo couponRepo;
    @Autowired
    private UserRepo userRepo;

    @PreAuthorize("hasAnyRole('USER','ADMIN', 'STAFF')")
    @Override
    public List<CouponStorageResponse> getAllCouponStorageByUserId(Integer userId ) {
        List<CouponStorage> couponStorages = couponStorageRepo.getAllCouponStorageByUserId(userId);
        couponStorages.stream()
                .map(couponStorageMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return couponStorages.stream()
                .map(couponStorageMapper :: convertEnToRes)
                .collect(Collectors.toList());
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN', 'STAFF')")
    @Override
    public Page<CouponStorageResponse> getCouponStorageByUserId(Integer userId ,Pageable pageable) {
        Page<CouponStorage> couponStoragesPage= couponStorageRepo.getCouponStorageByUserId(userId , pageable);
        List<CouponStorageResponse> couponStorageResponses = couponStoragesPage.getContent().stream()
                .map(couponStorageMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return new PageImpl<>(couponStorageResponses,pageable,couponStoragesPage.getTotalElements());
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN', 'STAFF')")
    @Override
    public Optional<CouponStorageResponse> getCouponStorageByCouponStorageId(Integer couponStorageId) {
        return Optional.empty();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN', 'STAFF')")
    @Override
    public CouponStorageResponse addCouponToCouponStorage(Integer userId, String code ) {
        CouponStorage couponStorage = new CouponStorage();
        Coupon coupon = couponRepo.findCouponByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Not found Coupon By Code"));
        couponStorage.setUser( userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("not found User")));
        couponStorage.setStatus(true);
        if (coupon.getUsedCount() < coupon.getUseLimit()){
            System.out.println("Coupon: " + coupon.getCouponId());
            couponStorage.setCoupon(coupon);
            CouponStorage couponStorageCreated = couponStorageRepo.save(couponStorage);
            return couponStorageMapper.convertEnToRes(couponStorageCreated);
        }
            return null;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN', 'STAFF')")
    @Override
    public CouponStorageResponse removeCouponInStorage(Integer couponStorageId) {
        CouponStorage couponStorage = couponStorageRepo.findById(couponStorageId)
                .orElseThrow(() -> new EntityNotFoundException("not found CouponStorage"));
        if(couponStorage != null){
            couponStorageRepo.delete(couponStorage);
        }
        return null;
    }
}

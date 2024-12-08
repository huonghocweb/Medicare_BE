package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.CouponStorage;
import poly.pharmacyproject.Model.Request.CouponStorageRequest;
import poly.pharmacyproject.Model.Response.CouponStorageResponse;

@Mapper(componentModel = "spring",  uses = {
        UserMapper.class , CouponMapper.class })
public interface CouponStorageMapper {

    CouponStorageResponse convertEnToRes(CouponStorage couponStorage);
    CouponStorage convertReqToEn(CouponStorageRequest couponStorageRequest);

}

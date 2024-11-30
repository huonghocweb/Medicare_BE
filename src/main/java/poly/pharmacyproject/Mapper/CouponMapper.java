package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.Coupon;
import poly.pharmacyproject.Model.Request.CouponRequest;
import poly.pharmacyproject.Model.Response.CouponResponse;


@Mapper(componentModel = "spring")
public interface  CouponMapper {

     CouponResponse convertEnToResponse(Coupon coupon);

     Coupon convertReqToEn(CouponRequest couponRequest);

}

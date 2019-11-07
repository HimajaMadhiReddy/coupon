package com.ucm.cupon.transformer;

import com.ucm.cupon.model.dto.Coupon;

public class DaoToDtoTransformer {

    public static Coupon DaoToDto(com.ucm.cupon.model.dao.Coupon coupon){

      Coupon couponDto = new Coupon();
     couponDto.setResourceId(coupon.getResourceId());
      couponDto.setPersonResourceId(coupon.getPersonResourceId());
      couponDto.setDateTime(coupon.getDateTime());
      couponDto.setStatus(coupon.getStatus());
      couponDto.setDiscount(coupon.getDiscount());
      couponDto.setCouponCode(coupon.getCouponCode());

      return couponDto;
    }
}

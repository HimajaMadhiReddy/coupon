package com.ucm.cupon.transformer;

import com.ucm.cupon.model.dao.Coupon;

public class DtoToDaoTransformer {

    public static Coupon DtoToDao(com.ucm.cupon.model.dto.Coupon coupon){

        Coupon couponDao = new Coupon();

        couponDao.setResourceId(coupon.getResourceId());
        couponDao.setStatus(coupon.getStatus());
        couponDao.setDateTime(coupon.getDateTime());
        couponDao.setPersonResourceId(coupon.getPersonResourceId());
        couponDao.setCouponCode(coupon.getCouponCode());
        couponDao.setDiscount(coupon.getDiscount());

        return couponDao;
    }
}

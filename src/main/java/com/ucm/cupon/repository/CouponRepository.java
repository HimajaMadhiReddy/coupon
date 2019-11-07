package com.ucm.cupon.repository;

import com.ucm.cupon.model.dao.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CouponRepository extends MongoRepository<Coupon,String> {

    Coupon findByResourceId(UUID resourceId);
    Coupon findCouponsByCouponCode(String couponCode);
    Coupon findByCouponCodeAndDiscount(String couponCode, String discount);

}

package com.ucm.cupon.service;


import com.ucm.cupon.exceptions.DuplicateRecordException;
import com.ucm.cupon.exceptions.InvalidRequestException;
import com.ucm.cupon.exceptions.ResourceNotFoundException;
import com.ucm.cupon.model.dto.Coupon;
import com.ucm.cupon.repository.CouponRepository;
import com.ucm.cupon.transformer.DaoToDtoTransformer;
import com.ucm.cupon.transformer.DtoToDaoTransformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;



public com.ucm.cupon.model.dto.Coupon validateCreateRequest(com.ucm.cupon.model.dto.Coupon coupon) throws InvalidRequestException,DuplicateRecordException{


coupon.setResourceId(UUID.randomUUID());

if(StringUtils.isNotBlank(coupon.getPersonResourceId())){
    try {
        UUID.fromString(coupon.getPersonResourceId());
    }catch (Exception ex){
        throw new InvalidRequestException("Person resource id is invalid.");
    }
}

if(StringUtils.isBlank(coupon.getCouponCode())){

    throw new InvalidRequestException("Please enter couponCode");
}

if(StringUtils.isBlank(coupon.getDiscount())){
    throw new InvalidRequestException("please enter discount");
}

try{

    Double.parseDouble(coupon.getDiscount());
}catch (Exception ex){
throw  new InvalidRequestException("please enter valid discountValue");
}
return DaoToDtoTransformer.DaoToDto( couponRepository.save(DtoToDaoTransformer.DtoToDao(coupon)));

}

    public Coupon getResourceId( String resourceId) throws InvalidRequestException, ResourceNotFoundException {

        try{
            UUID.fromString(resourceId);
        }catch (Exception ex){
            throw new InvalidRequestException("Please enter valid resourceId "+resourceId+" entered is Invalid.");
        }

        com.ucm.cupon.model.dao.Coupon couponDao = couponRepository.findByResourceId(UUID.fromString(resourceId));
        if(couponDao == null){
            throw  new ResourceNotFoundException("Coupon with the given resourceId " + resourceId+ " doesn't exist" );
        }
         return DaoToDtoTransformer.DaoToDto(couponDao);
    }

    public Coupon getCouponCode(String couponCode) throws ResourceNotFoundException {

        com.ucm.cupon.model.dao.Coupon coupon = couponRepository.findCouponsByCouponCode(couponCode);

        if(coupon == null){
            throw new ResourceNotFoundException("Coupon is invalid");
        }

    return DaoToDtoTransformer.DaoToDto(coupon);

    }

    public  Coupon validateUpdateRequest(String resourceId,Coupon coupon) throws InvalidRequestException, ResourceNotFoundException {

    try {
        UUID.fromString(resourceId);
    }catch (Exception ex){
        throw new InvalidRequestException("please enter valid resourceId " +resourceId+" entered is Invalid.");
    }
        com.ucm.cupon.model.dao.Coupon couponDao = couponRepository.findByResourceId(UUID.fromString(resourceId));
    if(couponDao == null){
        throw  new ResourceNotFoundException("Coupon with the given resourceId" + resourceId + " does't exist");
    }

    if(StringUtils.isNotBlank(coupon.getPersonResourceId())){
        couponDao.setPersonResourceId(coupon.getPersonResourceId());
    }
    if(StringUtils.isNotBlank(coupon.getDateTime())){
        couponDao.setDateTime(coupon.getDateTime());

    }
    if(StringUtils.isNotBlank(coupon.getDiscount())){
        couponDao.setDiscount(coupon.getDiscount());
    }
    if(coupon.getStatus()!= null){
        couponDao.setStatus(coupon.getStatus());
    }
    if(StringUtils.isNotBlank(coupon.getCouponCode())){
        couponDao.setCouponCode(coupon.getCouponCode());
    }

    return DaoToDtoTransformer.DaoToDto(couponRepository.save(couponDao));
    }

}

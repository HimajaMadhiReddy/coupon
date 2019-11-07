package com.ucm.cupon.controller;

import com.ucm.cupon.exceptions.DuplicateRecordException;
import com.ucm.cupon.exceptions.InvalidRequestException;
import com.ucm.cupon.exceptions.ResourceNotFoundException;
import com.ucm.cupon.model.dto.Coupon;
import com.ucm.cupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coupons")

public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping(method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    @ResponseBody
    Coupon createCupon(@RequestBody Coupon coupon)throws InvalidRequestException, DuplicateRecordException {

        return couponService.validateCreateRequest(coupon);
    }


    @ResponseBody
    @RequestMapping(value = "/{resourceId}",produces = "application/json",method = RequestMethod.GET)
    Coupon getByResourceId(@PathVariable String resourceId) throws InvalidRequestException, ResourceNotFoundException {

        return couponService.getResourceId(resourceId);


    }

    @ResponseBody
    @RequestMapping(value = "/{couponCode}",produces = "application/json")
    Coupon getByCouponCode(@PathVariable String couponCode) throws InvalidRequestException, ResourceNotFoundException {

        return couponService.getResourceId(couponCode);

    }

    @ResponseBody
    @RequestMapping(value = "/{resourceId}",produces = "application/json",method = RequestMethod.PUT)
     Coupon updateCoupon( @PathVariable String resourceId, @RequestBody Coupon coupon) throws InvalidRequestException, ResourceNotFoundException {

        return couponService.validateUpdateRequest(resourceId, coupon);

    }

}

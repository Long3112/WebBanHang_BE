package com.codegym.service;

import com.codegym.model.Coupon;
import com.codegym.model.Food;
import com.codegym.model.Shop;

import java.util.List;

public interface ICouponService  extends IGenerateService<Coupon>{

       List<Coupon> getCouponByFoodIdAndStatus(Long id,boolean status);
       List<Coupon> getCouponByFoodId(Long id);

       List<Coupon> getEnableCouponsByUserId(String id);
}

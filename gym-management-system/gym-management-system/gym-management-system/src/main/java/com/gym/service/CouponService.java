package com.gym.service;

import com.gym.pojo.Coupon;

import java.util.List;

public interface CouponService {

    List<Coupon> findAll();

    Boolean insertCoupon(Coupon coupon);

    Boolean updateCouponByCouponId(Coupon coupon);

    Boolean deleteByCouponId(Integer couponId);

    List<Coupon> selectByCouponId(Integer couponId);

    Integer selectTotalCount();
}

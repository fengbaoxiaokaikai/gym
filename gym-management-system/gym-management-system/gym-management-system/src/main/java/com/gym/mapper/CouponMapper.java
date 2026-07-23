package com.gym.mapper;

import com.gym.pojo.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {

    List<Coupon> findAll();

    Boolean insertCoupon(Coupon coupon);

    Boolean updateCouponByCouponId(Coupon coupon);

    Boolean deleteByCouponId(Integer couponId);

    List<Coupon> selectByCouponId(Integer couponId);

    Integer selectTotalCount();
}

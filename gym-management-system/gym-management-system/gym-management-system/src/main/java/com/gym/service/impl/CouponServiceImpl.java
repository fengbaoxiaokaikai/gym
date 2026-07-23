package com.gym.service.impl;

import com.gym.mapper.CouponMapper;
import com.gym.pojo.Coupon;
import com.gym.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public List<Coupon> findAll() {
        return couponMapper.findAll();
    }

    @Override
    public Boolean insertCoupon(Coupon coupon) {
        return couponMapper.insertCoupon(coupon);
    }

    @Override
    public Boolean updateCouponByCouponId(Coupon coupon) {
        return couponMapper.updateCouponByCouponId(coupon);
    }

    @Override
    public Boolean deleteByCouponId(Integer couponId) {
        return couponMapper.deleteByCouponId(couponId);
    }

    @Override
    public List<Coupon> selectByCouponId(Integer couponId) {
        return couponMapper.selectByCouponId(couponId);
    }

    @Override
    public Integer selectTotalCount() {
        return couponMapper.selectTotalCount();
    }
}

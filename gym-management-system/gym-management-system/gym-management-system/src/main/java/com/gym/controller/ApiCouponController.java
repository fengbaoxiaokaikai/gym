package com.gym.controller;

import com.gym.pojo.Coupon;
import com.gym.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coupon")
public class ApiCouponController {

    private final CouponService couponService;

    public ApiCouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/selCoupon")
    public Map<String, Object> selectCoupon() {
        List<Coupon> couponList = couponService.findAll();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("couponList", couponList);
        return resp;
    }

    @GetMapping("/toAddCoupon")
    public Map<String, Object> toAddCoupon() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        return resp;
    }

    @PostMapping("/addCoupon")
    public ResponseEntity<Map<String, Object>> addCoupon(Coupon coupon) {
        couponService.insertCoupon(coupon);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/delCoupon")
    public ResponseEntity<Map<String, Object>> deleteCoupon(Integer couponId) {
        couponService.deleteByCouponId(couponId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/toUpdateCoupon")
    public Map<String, Object> toUpdateCoupon(Integer couponId) {
        List<Coupon> couponList = couponService.selectByCouponId(couponId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("couponList", couponList);
        return resp;
    }

    @PostMapping("/updateCoupon")
    public ResponseEntity<Map<String, Object>> updateCoupon(Coupon coupon) {
        couponService.updateCouponByCouponId(coupon);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        return ResponseEntity.ok(resp);
    }
}

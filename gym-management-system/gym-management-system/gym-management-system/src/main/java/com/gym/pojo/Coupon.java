package com.gym.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    private Integer couponId;
    private String couponCode;
    private String couponName;
    private String couponType;
    private BigDecimal discountAmount;
    private BigDecimal minimumAmount;
    private String startTime;
    private String endTime;
    private String couponStatus;
    private Integer couponStock;
    private String couponMessage;
}

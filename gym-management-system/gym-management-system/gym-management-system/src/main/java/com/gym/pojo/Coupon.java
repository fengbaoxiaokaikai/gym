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
    
    // 优惠券使用状态: 0-未使用, 1-已使用, 2-已过期, 3-已作废
    private Integer status;
    
    // 关联用户ID
    private Integer userId;
}

package com.example.assignment.service;

import com.example.assignment.domain.Coupon;
import com.example.assignment.domain.CouponStatus;
import com.example.assignment.domain.DiscountMethod;
import com.example.assignment.domain.DiscountRange;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class CouponDto {

    private DiscountMethod method;
    private int discountVal;
    private DiscountRange range;
    private CouponStatus status;
    private String appliedItemName;

    public CouponDto(DiscountMethod method, int discountVal, DiscountRange range, CouponStatus status) {
        this.method = method;
        this.discountVal = discountVal;
        this.range = range;
        this.status = status;
    }

    public static CouponDto toDto(Coupon coupon){

        if(Objects.nonNull(coupon.getCouponAppliedItem())){
            return new CouponDto(
                    coupon.getMethod(),coupon.getDiscountVal(),coupon.getRange(),
                    coupon.getStatus(),coupon.getCouponAppliedItem().getItem().getName());
        }

        return new CouponDto(coupon.getMethod(),coupon.getDiscountVal(),coupon.getRange(),coupon.getStatus());
    }
}

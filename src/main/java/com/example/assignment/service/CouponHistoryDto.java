package com.example.assignment.service;

import com.example.assignment.domain.CouponHistory;
import com.example.assignment.domain.CouponHistoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class CouponHistoryDto {

    private Long id;
    private CouponHistoryStatus status;
    private CouponDto couponDto;

    public CouponHistoryDto(Long id, CouponHistoryStatus status) {
        this.id = id;
        this.status = status;
    }

    public void addCoupon(CouponDto couponDto){
        this.couponDto = couponDto;
    }

    public static CouponHistoryDto toDto(CouponHistory couponHistory){
        return new CouponHistoryDto(couponHistory.getId(),couponHistory.getStatus());
    }
}

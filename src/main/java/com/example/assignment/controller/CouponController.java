package com.example.assignment.controller;

import com.example.assignment.commons.config.interceptor.Auth;
import com.example.assignment.domain.UserRole;
import com.example.assignment.dto.CouponHistoryDto;
import com.example.assignment.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/coupon")
@RestController
public class CouponController {

    private final CouponService couponService;

    @Auth(role = UserRole.STANDARD)
    @RequestMapping("/list")
    public ResponseEntity<List<CouponHistoryDto>> readCouponList(@RequestParam String loginId){

        List<CouponHistoryDto> couponList = couponService.findCouponListByLoginId(loginId);

        return ResponseEntity.ok(couponList);
    }

}

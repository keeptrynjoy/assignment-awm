package com.example.assignment.service;

import com.example.assignment.domain.Coupon;
import com.example.assignment.domain.CouponHistory;
import com.example.assignment.domain.Users;
import com.example.assignment.repository.CouponHistoryRepository;
import com.example.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CouponService {

    private final CouponHistoryRepository couponHistoryRepository;

    private final UserRepository userRepository;

    public List<CouponHistoryDto> findCouponListByLoginId(String loginId){

        Users user = userRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalArgumentException("로그인 아이디에 해당하는 회원정보가 없습니다."));

        List<CouponHistory> couponHistoryList = couponHistoryRepository.findByUsers(user);
        List<CouponHistoryDto> couponHistoryDtoList = new ArrayList<>();

        for(CouponHistory couponHistory:couponHistoryList){

            Coupon coupon = couponHistory.getCoupon();
            CouponDto couponDto = CouponDto.toDto(coupon);

            CouponHistoryDto couponHistoryDto = CouponHistoryDto.toDto(couponHistory);
            couponHistoryDto.addCoupon(couponDto);

            couponHistoryDtoList.add(couponHistoryDto);
        }

        return couponHistoryDtoList;
    }

    public CouponHistory findCouponHistory(Long couponHisId){
        return couponHistoryRepository.findById(couponHisId)
                .orElseThrow(IllegalArgumentException::new);
    }

}

package com.example.assignment.service;

import com.example.assignment.domain.CouponHistory;
import com.example.assignment.repository.CouponHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CouponService {

    private final CouponHistoryRepository couponHistoryRepository;

    public CouponHistory findCouponHistory(Long couponHisId){
        return couponHistoryRepository.findById(couponHisId)
                .orElseThrow(IllegalArgumentException::new);
    }

}

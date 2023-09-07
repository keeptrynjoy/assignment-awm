package com.example.assignment.service;

import com.example.assignment.domain.CouponHistory;
import com.example.assignment.domain.DiscountRange;
import com.example.assignment.domain.Orders;
import com.example.assignment.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CouponService couponService;

    public int getTotalItemAmount(Long orderId) {

        Orders order = findOrderById(orderId);

        return order.getTotalItemAmount() + order.getDeliveryCost();
    }

//    public int getPaymentAmount(Long orderId, Long couponHisId) {
//        Orders order = findOrderById(orderId);
//        final int totalItemAmount = order.getTotalItemAmount();
//
//        if(Objects.nonNull(couponHisId)){
//
//            CouponHistory couponHistory = couponService.findCouponHistory(couponHisId);
//            if(couponHistory.getDiscount().getRange().equals(DiscountRange.INDIVIDUAL_ITEM)){
//                    couponHistory.getCoupon().getCouponAppliedItems()
//            }
//
//        }
//
//        return 0;
//    }

    private Orders findOrderById(Long orderId){
        // TODO: 커스텀 예외로 변경하기
        return orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);
    }

}

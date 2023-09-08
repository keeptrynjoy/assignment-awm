package com.example.assignment.service;

import com.example.assignment.domain.*;
import com.example.assignment.repository.OrderRepository;
import com.example.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CouponService couponService;
    private final UserRepository userRepository;

    public int getTotalOrderAmount(Long orderId) {

        final Orders order = findOrderById(orderId);

        return order.getTotalItemAmount() + order.getDeliveryCost();
    }

    public PaymentResultDto getPaymentCost(Long orderId, Long couponHisId) {

        final Orders order = findOrderById(orderId);

        final int totalOrderAmount = order.getTotalItemAmount();
        final int deliveryFee = order.getDeliveryCost();
        int discountAmount = 0;
        int duePayment = 0;

        if(Objects.nonNull(couponHisId)){

            CouponHistory couponHistory = couponService.findCouponHistory(couponHisId);

            couponHistory.validUsableState();

            final Coupon coupon = couponHistory.getCoupon();
            final DiscountMethod method = coupon.getMethod();

            if(coupon.getRange().equals(DiscountRange.INDIVIDUAL_ITEM)){
                final Item item = couponHistory.getItemByCoupon();

                final int targetAmount = order.getOrderAmountOfItem(item);
                final int calculateAmount = method.calculate(targetAmount, coupon.getDiscountVal());

                discountAmount = targetAmount - calculateAmount;
                duePayment = (totalOrderAmount - discountAmount) + deliveryFee;

            } else {
                final int calculateAmount = method.calculate(order.getTotalItemAmount(), coupon.getDiscountVal());

                discountAmount = totalOrderAmount - calculateAmount;
                duePayment = calculateAmount + deliveryFee;
            }

        }
        return new PaymentResultDto(totalOrderAmount,discountAmount,deliveryFee,duePayment);
    }


    public List<OrderDto> findAllOrder(String loginId) {

        Users user = userRepository.findByLoginId(loginId)
                .orElseThrow(()-> new IllegalArgumentException("로그인 아이디에 해당하는 회원정보가 없습니다."));

        List<Orders> orders = user.getOrders();
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        for(Orders order : orders) {
            OrderDto orderDto = OrderDto.toDto(order);

            for(OrderItem orderItem : order.getOrderItems()){
                OrderItemDto orderItemDto = OrderItemDto.toDto(orderItem);
                orderItemDtoList.add(orderItemDto);
            }

            orderDto.addOrderList(orderItemDtoList);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    private Orders findOrderById(Long orderId){
        // TODO: 커스텀 예외로 변경하기
        return orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);
    }
}

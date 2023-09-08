package com.example.assignment.controller;

import com.example.assignment.commons.config.interceptor.Auth;
import com.example.assignment.domain.UserRole;
import com.example.assignment.dto.OrderDto;
import com.example.assignment.service.OrderService;
import com.example.assignment.dto.PaymentResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @Auth(role = UserRole.STANDARD)
    @GetMapping("/info")
    public ResponseEntity<List<OrderDto>> readOrder(@RequestParam String loginId){

        List<OrderDto> orderDtoList = orderService.findAllOrder(loginId);

        return ResponseEntity.ok(orderDtoList);
    }


    // 주문 총 금액 계산
    @Auth(role = UserRole.STANDARD)
    @GetMapping("/{id}/amount")
    public ResponseEntity<Integer> calculateTotalAmount(@PathVariable(value = "id") Long orderId){

        final int totalOrderAmount = orderService.getTotalOrderAmount(orderId);

        return ResponseEntity.ok(totalOrderAmount);
    }

    // 주문 결제 금액 계산
    @Auth(role = UserRole.STANDARD)
    @GetMapping("/{id}/payment")
    public ResponseEntity<PaymentResultDto> calculatePaymentCost(@PathVariable(value = "id") Long orderId,
                                                                 @RequestParam(required = false) Long couponId){

        final PaymentResultDto totalPaymentAmount = orderService.getPaymentCost(orderId, couponId);

        return ResponseEntity.ok(totalPaymentAmount);
    }
}

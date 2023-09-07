package com.example.assignment.controller;

import com.example.assignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    // 주문 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<OrderResponseDto> orderInfo(@PathVariable(value = "id") Integer orderId){
//
//    }

//    // 주문 총 금액 계산
//    @GetMapping("/{id}")
//    public ResponseEntity<Integer> calculateTotalAmount(@PathVariable(value = "id") Long orderId){
//
//        final int totalOrderAmount = orderService.getTotalOrderAmount(orderId);
//
//        return ResponseEntity.ok(totalOrderAmount);
//    }

    // 주문 결제 금액 계산
//    @GetMapping("/{id}")
//    public ResponseEntity<Integer> calculatePaymentAmount(@PathVariable(value = "id") Long orderId,
//                                                        @RequestParam(required = false) Long coupon){
//
//        final int totalPaymentAmount = orderService.getPaymentAmount(orderId, coupon);
//        return ResponseEntity.ok();
//    }
}

package com.example.assignment.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResultDto {
    private int totalOrderAmount;
    private int discountAmount;
    private int deliveryFee;
    private int duePayment;
}

package com.example.assignment.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
public class CouponDiscount {
    @Enumerated(EnumType.STRING)
    private DiscountMethod method;

    private int discountVal;

    @Enumerated(EnumType.STRING)
    private DiscountRange range;
}

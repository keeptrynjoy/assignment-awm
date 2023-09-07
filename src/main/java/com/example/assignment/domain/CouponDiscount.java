package com.example.assignment.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class CouponDiscount {
    @Enumerated(EnumType.STRING)
    private DiscountMethod method;

    private int discountVal;

    @Enumerated(EnumType.STRING)
    private DiscountRange range;
}

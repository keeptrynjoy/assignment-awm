package com.example.assignment.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponAppliedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_applied_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(fetch = FetchType.LAZY)
    private Coupon coupon;
}

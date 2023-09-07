package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Embedded
    private CouponDiscount couponDiscount;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<CouponAppliedItem> couponAppliedItems = new ArrayList<>();

    @OneToMany(mappedBy = "coupon")
    private List<CouponHistory> couponHistories = new ArrayList<>();
}

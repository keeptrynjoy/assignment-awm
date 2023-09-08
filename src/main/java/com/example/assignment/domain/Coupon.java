package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiscountMethod method;

    private int discountVal;

    @Enumerated(EnumType.STRING)
    private DiscountRange range;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_applied_id")
    private CouponAppliedItem couponAppliedItem;

    @OneToMany(mappedBy = "coupon")
    private List<CouponHistory> couponHistories = new ArrayList<>();
}

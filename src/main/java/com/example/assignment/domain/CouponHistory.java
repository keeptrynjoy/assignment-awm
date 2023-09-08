package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CouponHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_his_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CouponHistoryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public void validUsableState(){
        switch (status){
            case USED:
                throw new IllegalArgumentException("사용된 쿠폰입니다.");
            case UNUSABLE:
                throw new IllegalArgumentException("사용이 불가능한 쿠폰입니다.");

        }
    }

    public Item getItemByCoupon(){
        return coupon.getCouponAppliedItem().getItem();
    }
}

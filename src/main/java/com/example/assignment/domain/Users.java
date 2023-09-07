package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String loginId;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "users")
    private List<Orders> orders;

    @OneToMany(mappedBy = "users")
    private List<CouponHistory> couponHistories;
}

package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import lombok.*;
import org.hibernate.envers.AuditJoinTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @OneToMany(mappedBy = "item")
    private List<ItemHistory> itemHistories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    @AuditJoinTable
    private List<CouponAppliedItem> couponAppliedItems = new ArrayList<>();


}

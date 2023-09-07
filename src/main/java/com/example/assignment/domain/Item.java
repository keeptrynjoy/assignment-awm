package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import com.example.assignment.service.ItemDto;
import lombok.*;
import org.hibernate.envers.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Audited
@AuditTable("item_history")
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

    @OneToMany
    @NotAudited
    private List<CouponAppliedItem> couponAppliedItems = new ArrayList<>();

    public void updateItem(ItemDto itemDto){
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
        this.stockQuantity = itemDto.getStockQuantity();
        this.status = itemDto.getStatus();
    }

    public void softDeleteItem(){
        this.status = ItemStatus.DELETED;
    }
}

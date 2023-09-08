package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import com.example.assignment.service.ItemDto;
import lombok.*;
import org.hibernate.envers.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemHistory> itemHistories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CouponAppliedItem> couponAppliedItems = new ArrayList<>();
    public void addItemHistory(ItemHistory itemHistory){
        itemHistories.add(itemHistory);
        itemHistory.addItem(this);
    }

    public void updateItem(ItemDto itemDto){

        if(itemDto.getStockQuantity() == 0){
            this.status = ItemStatus.SOLD_OUT;
        } else{
            this.status = itemDto.getStatus();
        }

        if (itemDto.getStatus() == ItemStatus.SOLD_OUT) {
            this.stockQuantity = 0;
        } else {
            this.stockQuantity = itemDto.getStockQuantity();
        }

        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
        addItemHistory(new ItemHistory(itemDto.getPrice(), LocalDateTime.now()));
    }

    public void softDeleteItem(){
        this.status = ItemStatus.DELETED;
    }

    public void addHistory(){
        this.itemHistories.add(new ItemHistory(this.price, LocalDateTime.now()));
    }
}

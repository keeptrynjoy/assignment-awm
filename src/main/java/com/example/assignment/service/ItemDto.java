package com.example.assignment.service;

import com.example.assignment.domain.Item;
import com.example.assignment.domain.ItemStatus;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class ItemDto {

    @NotNull
    private String name;

    @Min(0)
    private int price;

    @Min(0)
    private int stockQuantity;

    private ItemStatus status;

    public Item toEntity(){
        return Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .status(status)
                .build();
    }
}

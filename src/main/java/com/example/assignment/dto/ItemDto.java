package com.example.assignment.dto;

import com.example.assignment.domain.Item;
import com.example.assignment.domain.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class ItemDto {

    private Long id;

    @NotNull
    private String name;

    @Min(0)
    private int price;

    @Min(0)
    private int stockQuantity;

    private ItemStatus status;

    public static ItemDto toDto(Item item){
        return new ItemDto(item.getId(),item.getName(),item.getPrice(), item.getStockQuantity(), item.getStatus());
    }

    public Item toEntity(){
        return Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .status(status)
                .build();
    }
}

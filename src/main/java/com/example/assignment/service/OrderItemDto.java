package com.example.assignment.service;

import com.example.assignment.domain.ItemStatus;
import com.example.assignment.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private String itemName;
    private ItemStatus itemStatus;
    private int orderPrice;
    private int count;

    public static OrderItemDto toDto(OrderItem orderItem){
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getItem().getName(),
                orderItem.getItem().getStatus(),
                orderItem.getOrderPrice(),
                orderItem.getCount());
    }
}

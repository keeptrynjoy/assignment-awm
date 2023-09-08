package com.example.assignment.dto;

import com.example.assignment.domain.OrderStatus;
import com.example.assignment.domain.Orders;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class OrderDto {

    private Long id;

    @NotNull
    private OrderStatus status;
    @Min(0)
    private int deliveryCost;

    private List<OrderItemDto> orderItemList;

    public OrderDto(Long id, OrderStatus status, int deliveryCost) {
        this.id = id;
        this.status = status;
        this.deliveryCost = deliveryCost;
    }

    public void addOrderList(List<OrderItemDto> orderItemDtoList){
        this.orderItemList = orderItemDtoList;
    }

    public static OrderDto toDto(Orders order) {
        return new OrderDto(order.getId(), order.getStatus(), order.getDeliveryCost());
    }
}

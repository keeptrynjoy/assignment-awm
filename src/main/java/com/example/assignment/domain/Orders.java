package com.example.assignment.domain;

import com.example.assignment.commons.support.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Orders extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private int deliveryCost;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public int getTotalItemAmount(){
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

}

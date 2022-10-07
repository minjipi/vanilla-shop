package com.minji.vanillashop.domain.delivery.entity;

import com.minji.vanillashop.domain.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}

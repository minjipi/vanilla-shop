package com.minji.vanillashop.domain.order.dto.domain;

import com.minji.vanillashop.domain.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class MemberOrderDetail {
    private String memberEmail;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
}




package com.minji.vanillashop.domain.order.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class OrderDetail {
    private Long orderNo;
    private Long orderItemNo;
    private String title;
    private LocalDateTime orderDate;
}

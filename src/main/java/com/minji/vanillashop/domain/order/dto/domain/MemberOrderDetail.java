package com.minji.vanillashop.domain.order.dto.domain;

import com.minji.vanillashop.domain.order.entity.Order;
import com.minji.vanillashop.domain.order.entity.OrderItem;
import com.minji.vanillashop.domain.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@ToString
@AllArgsConstructor
public class MemberOrderDetail {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems;

    public MemberOrderDetail(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(orderItem))
                .collect(toList());
    }
}


@Data
class OrderItemDto {

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getProduct().getTitle();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}




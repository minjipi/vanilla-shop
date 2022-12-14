package com.minji.vanillashop.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minji.vanillashop.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    //==생성 메서드==//
    @Builder
    public OrderItem(Product product, int orderPrice, int count) {
        this.product = product;
        this.orderPrice = orderPrice;
        this.count = count;
        product.removeStock(count);
    }

//    public static OrderItem createOrderItem(Product product, int orderPrice, int count) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setProduct(product);
//        orderItem.setOrderPrice(orderPrice);
//        orderItem.setCount(count);

//        product.removeStock(count);
//        return orderItem;
//    }

    //==비즈니스 로직==//
    public void cancel() {
        getProduct().addStock(count);
    }

    //==조회 로직==//
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}

package com.minji.vanillashop.domain.order.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;


    @Builder
    public OrderItem(Product product, int orderPrice, int count) {
        this.product = product;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public void cancel() {
        getProduct().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}

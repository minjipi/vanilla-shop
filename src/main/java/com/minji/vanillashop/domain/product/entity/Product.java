package com.minji.vanillashop.domain.product.entity;


import com.minji.vanillashop.domain.member.entity.BaseEntity;
import com.minji.vanillashop.domain.product.entity.ProductImage;

import com.minji.vanillashop.global.exceptions.NotEnoughStockException;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long pno;

    private String title;
    private int price;
    private int stockQuantity;

    //    비즈니스 로직
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int reStock = this.stockQuantity - quantity;
        if (reStock < 0) {
            throw new NotEnoughStockException("need more stock)");
        }
        this.stockQuantity = reStock;
    }

    public void changeName(String title) {
        this.title = title;
    }

    public void changeStock(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

package com.minji.vanillashop.domain.api;

import com.minji.vanillashop.domain.delivery.entity.Delivery;
import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.order.entity.Order;
import com.minji.vanillashop.domain.order.entity.OrderItem;
import com.minji.vanillashop.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static com.minji.vanillashop.domain.member.entity.MemberRole.USER;

/**
 * 종 주문 2개
 * * userA
 * * pajamas1,2
 * * userB
 * * pajamas3,4
 */

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member1 = Member.builder()
                    .email("minji1@minji.com")
                    .name("minji1")
                    .password("1234")
                    .roleSet(USER)
                    .build();

            em.persist(member1);

            Product pajamas1 = Product.builder()
                    .title("pajamas1")
                    .price(1000)
                    .stockQuantity(100)
                    .build();
            em.persist(pajamas1);


            Product pajamas2 = Product.builder()
                    .title("pajamas2")
                    .price(2000)
                    .stockQuantity(100)
                    .build();

            em.persist(pajamas2);

            OrderItem orderItem1 = OrderItem.builder()
                    .product(pajamas1)
                    .orderPrice(2000)
                    .count(2)
                    .build();

            OrderItem orderItem2 = OrderItem.builder()
                    .product(pajamas2)
                    .orderPrice(2000)
                    .count(2)
                    .build();

            Delivery delivery1 = new Delivery();

            Order order = Order.builder()
                    .member(member1)
                    .delivery(delivery1)
                    .orderItems(Order.createOrderItem(orderItem1, orderItem2))
                    .build();

            em.persist(order);
        }

        public void dbInit2() {
            Member member2 = Member.builder()
                    .email("minji2@minji.com")
                    .name("minji2")
                    .password("1234")
                    .roleSet(USER)
                    .build();

            em.persist(member2);

            Product pajamas3 = Product.builder()
                    .title("pajamas3")
                    .price(2000)
                    .stockQuantity(100)
                    .build();

            em.persist(pajamas3);

            Product pajamas4 = Product.builder()
                    .title("pajamas4")
                    .price(4000)
                    .stockQuantity(100)
                    .build();

            em.persist(pajamas4);

            OrderItem orderItem3 = OrderItem.builder()
                    .product(pajamas3)
                    .orderPrice(2000)
                    .count(3)
                    .build();

            OrderItem orderItem4 = OrderItem.builder()
                    .product(pajamas4)
                    .orderPrice(2000)
                    .count(4)
                    .build();


            Delivery delivery2 = new Delivery();


            Order order = Order.builder()
                    .member(member2)
                    .delivery(delivery2)
                    .orderItems(Order.createOrderItem(orderItem3, orderItem4))
                    .build();

            em.persist(order);
        }

    }
}


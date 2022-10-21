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

/**
 * 종 주문 2개
 * * userA
 * * pajamas1
 * * pajamas2
 * * userB
 * * SPRING1 Product
 * * SPRING2 Product
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
            System.out.println("Init1" + this.getClass());
            Member member = createMember("userA", "minji1");
            em.persist(member);

            Product pajamas1 = createProduct("pajamas1", 10000, 100);
            em.persist(pajamas1);

            Product pajamas2 = createProduct("pajamas2", 20000, 100);
            em.persist(pajamas2);

            System.out.println(pajamas1.getStockQuantity());
            System.out.println("================");

            OrderItem orderItem1 = OrderItem.createOrderItem(pajamas1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(pajamas2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = createMember("userB", "minji2");
            em.persist(member);

            Product pajamas1 = createProduct("pajamas1", 20000, 200);
            em.persist(pajamas1);

            Product pajamas2 = createProduct("pajamas2", 40000, 300);
            em.persist(pajamas2);

            OrderItem orderItem1 = OrderItem.createOrderItem(pajamas1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(pajamas2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, String email) {
            Member member = Member.builder()
                    .name(name)
                    .email(email)
                    .build();
            return member;
        }

        private Product createProduct(String name, int price, int stockQuantity) {
            Product pajamas = Product.builder()
                    .title(name)
                    .price(price)
                    .stockQuantity(stockQuantity).build();

            return pajamas;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            return delivery;
        }
    }
}


package com.minji.vanillashop.domain.order.service;

import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.repository.MemberRepository;
import com.minji.vanillashop.domain.order.entity.Order;
import com.minji.vanillashop.domain.order.entity.OrderStatus;
import com.minji.vanillashop.domain.order.repository.OrderRepository;
import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.product.repository.ProductRepository;
import com.minji.vanillashop.global.exceptions.NotEnoughStockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = createMember();

        Product product = createProduct("Item1", 10, 2);

        int orderCount = 2;

        Long orderId = orderService.createOrder(member.getId(), product.getPno(), orderCount);

        Order getOrder = orderRepository.findById(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수 확인");
        assertEquals(10 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량");
        assertEquals(0, product.getStockQuantity(), "주문 수량만큼 재고가 줄어야함");
    }

    private Product createProduct(String title, int price, int stockQuantity) {
        Product product = Product.builder()
                .title(title)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
        productRepository.save(product);
        return product;
    }

    private Member createMember() {
        Member member = Member.builder()
                .name("minji")
                .email("minji1@minji.com")
                .password("minji")
                .build();
        memberRepository.save(member);
        return member;
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {

        Member member = createMember();
        Product product = createProduct("Item2", 100, 10);
        int orderCount = 11;

        assertThrows(NotEnoughStockException.class,
                () -> orderService.createOrder(member.getId(), product.getPno(), orderCount), "재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 주문취소() throws Exception {

        //given
        Member member = createMember();

        int stockQuantity = 10;
        Product product = createProduct("Item3", 100, stockQuantity);

        int orderCount = 2;

        Long orderId = orderService.createOrder(member.getId(), product.getPno(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findById(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "상품 주문 취소시 상태는 CANCEL");
        assertEquals(10, product.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야한다.");
    }
}
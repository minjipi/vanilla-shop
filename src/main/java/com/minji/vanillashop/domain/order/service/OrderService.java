package com.minji.vanillashop.domain.order.service;

import com.minji.vanillashop.domain.delivery.entity.Delivery;
import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.repository.MemberRepository;
import com.minji.vanillashop.domain.order.dto.domain.MemberOrderDetail;
import com.minji.vanillashop.domain.order.dto.request.MemberOrderQuery;
import com.minji.vanillashop.domain.order.entity.Order;
import com.minji.vanillashop.domain.order.entity.OrderItem;
import com.minji.vanillashop.domain.order.repository.OrderRepository;
import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.product.repository.ProductRepository;
import com.minji.vanillashop.global.exceptions.MemberNotFoundException;
import com.minji.vanillashop.global.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 주문
    @Transactional
    public Long createOrder(Long memberId, Long productId, Integer count) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 사용자 입니다."));


        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("존재하지 않는 상품 입니다."));

        Delivery delivery = new Delivery();

        //주문상품 생성
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .orderPrice(product.getPrice())
                .count(count)
                .build();

        //주문 생성
        Order order = Order.builder()
                .delivery(delivery)
                .orderItems(Order.createOrderItem(orderItem))
                .build();

//        Order order = Order.createOrder(member.get(), delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        order.registerMember(member);

        return order.getId();
    }

    //    취소
//    @Transactional
//    public void cancelOrder(Long orderId) {
//        Order order = orderRepository.findById(orderId);
//        order.cancel();
//    }


//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAllByString(orderSearch);
//    }

    @Transactional(readOnly = true)
    public List<MemberOrderDetail> readMemberOrderDetailList(MemberOrderQuery query){
        return orderRepository.findMemberOrderDetailListBy(query);
    }


}

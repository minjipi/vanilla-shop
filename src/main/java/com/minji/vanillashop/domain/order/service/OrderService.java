package com.minji.vanillashop.domain.order.service;

import com.minji.vanillashop.domain.delivery.entity.Delivery;
import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.repository.MemberRepository;
import com.minji.vanillashop.domain.order.entity.Order;
import com.minji.vanillashop.domain.order.entity.OrderItem;
import com.minji.vanillashop.domain.order.entity.OrderSearch;
import com.minji.vanillashop.domain.order.repository.OrderRepository;
import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long productId, int count) {
        //entity 조회
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Product> product = productRepository.findById(productId);

        //배송 정보
        Delivery delivery = new Delivery();

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(product.get(), product.get().getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member.get(), delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }



//    취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }


    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
}

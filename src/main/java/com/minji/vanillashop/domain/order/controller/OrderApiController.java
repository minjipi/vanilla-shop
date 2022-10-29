package com.minji.vanillashop.domain.order.controller;

import com.minji.vanillashop.domain.order.dto.PostOrderDto;
import com.minji.vanillashop.domain.order.dto.domain.MemberOrderDetail;
import com.minji.vanillashop.domain.order.dto.request.MemberOrderQuery;
import com.minji.vanillashop.domain.order.repository.OrderRepository;
import com.minji.vanillashop.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Long> createOrder(@RequestBody PostOrderDto postOrderDto) {
        System.out.println("memberId: " + postOrderDto.getMemberId() + ", productId: " + postOrderDto.getProductId() + ", count: " + postOrderDto.getCount());
        return new ResponseEntity<>(orderService.createOrder(postOrderDto.getMemberId(), postOrderDto.getProductId(), postOrderDto.getCount()), HttpStatus.OK);
    }


    @GetMapping("/orders")
    public ResponseEntity<List<MemberOrderDetail>> ordersV3_page(
            @RequestParam(required = false) String memberEmail,
            @RequestParam(required = false, value = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, value = "limit", defaultValue = "0") int limit) {

        MemberOrderQuery query = MemberOrderQuery.builder()
                .memberEmail(memberEmail)
                .offset(offset)
                .limit(limit)
                .build();

//        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
//        List<MemberOrderDetail> result = orders.stream()
//                .map(o -> new MemberOrderDetail(o))
//                .collect(toList());

        return new ResponseEntity<>(orderService.readMemberOrderDetailList(query), HttpStatus.OK);
    }
}
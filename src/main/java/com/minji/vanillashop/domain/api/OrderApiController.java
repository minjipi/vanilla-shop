package com.minji.vanillashop.domain.api;

import com.minji.vanillashop.domain.order.repository.OrderRepository;
import com.minji.vanillashop.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @PostMapping("/api/order")
    public ResponseEntity<String> createOrder(@RequestParam("memberId") Long memberId,
                                              @RequestParam("productId") Long productId,
                                              @RequestParam("count") int count) {
        System.out.println("memberId: "+memberId + ", productId: "+ ", count: ");
        return new ResponseEntity<>(orderService.createOrder(memberId, productId, count), HttpStatus.OK);
    }


}
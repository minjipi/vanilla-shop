package com.minji.vanillashop.domain.order.controller;

import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.service.MemberService;
import com.minji.vanillashop.domain.order.dto.PostOrderDto;
import com.minji.vanillashop.domain.order.service.OrderService;
import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Product> products = productService.findProducts();

        model.addAttribute("members", members);
        model.addAttribute("product", products);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestBody PostOrderDto postOrderDto) {

//    public String order(@RequestParam("memberId") Long memberId,
//                        @RequestParam("productId") Long productId,
//                        @RequestParam("count") int count) {

        orderService.createOrder(postOrderDto.getMemberId(), postOrderDto.getProductId(), postOrderDto.getCount());
        return "redirect:/orders";
    }

//    @GetMapping("/orders")
//    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
//        List<Order> orders = orderService.findOrders(orderSearch);
//        model.addAttribute("orders", orders);
//
//        return "order/orderList";
//    }

//    @PostMapping("/orders/{orderId}/cancel")
//    public String cancelOrder(@PathVariable("orderId") Long orderId) {
//        orderService.cancelOrder(orderId);
//        return "redirect:/orders";
//    }

}

package com.minji.vanillashop.domain.order.controller;

import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.service.MemberService;
import com.minji.vanillashop.domain.order.entity.Order;
import com.minji.vanillashop.domain.order.entity.OrderSearch;
import com.minji.vanillashop.domain.order.repository.OrderRepository;
import com.minji.vanillashop.domain.order.service.OrderService;
import com.minji.vanillashop.domain.product.entity.Product;
import com.minji.vanillashop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

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
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("productId") Long productId,
                        @RequestParam("count") int count) {

        orderService.order(memberId, productId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

}

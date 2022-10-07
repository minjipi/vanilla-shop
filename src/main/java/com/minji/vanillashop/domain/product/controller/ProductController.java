package com.minji.vanillashop.domain.product.controller;

import com.minji.vanillashop.domain.product.dto.PageRequestDTO;
import com.minji.vanillashop.domain.product.dto.ProductDTO;
import com.minji.vanillashop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(ProductDTO productDTO, RedirectAttributes redirectAttributes) {
        log.info("productDTO: " + productDTO);


        Long pno = productService.register(productDTO);

        redirectAttributes.addFlashAttribute("msg", pno);

        return "redirect:/product/list";
    }

    @GetMapping("/update/{pno}")
    public String update(ProductDTO productDTO, Model model) {

        System.out.println("==================== productDTO: " + productDTO);
        System.out.println("==================== model: " + model);

        ProductDTO dto = ProductDTO.builder()
                .title(productDTO.getTitle())
                .stockQuantity(productDTO.getStockQuantity())
                .build();

        model.addAttribute("dto", productDTO);
        return "product/upadateForm";
    }

    @PostMapping("/update/{pno}")
    public String update(@PathVariable Long pno, @ModelAttribute("form") ProductDTO dto) {

        System.out.println("==================== productDTO: " + dto);

        ProductDTO productDTO = ProductDTO.builder()
                .pno(pno)
                .title(dto.getTitle())
                .stockQuantity(dto.getStockQuantity())
                .build();

        productService.update(productDTO);

        return "redirect:/product/list";
    }

//
//
//

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("pageRequestDTO: " + pageRequestDTO);

        model.addAttribute("result", productService.getList(pageRequestDTO));

    }

    @GetMapping({"/read", "/modify"})
    public void read(long pno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("pno: " + pno);
        ProductDTO productDTO = productService.getProduct(pno);
        model.addAttribute("dto", productDTO);
    }

}

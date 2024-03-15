package com.example.kledo.productkledo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kledo.productkledo.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("/all")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "product/productList";
    }
    

}

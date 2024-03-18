package com.example.kledo.productkledo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kledo.productkledo.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("/all")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProductsWithTotalQty());
        return "product/productList";
    }
    
    @GetMapping("/{id}")
    public String showWarehouse(@PathVariable("id") int id, Model model) {
        model.addAttribute("products", productService.getAllProductsWithWarehouseId(id));
        // model.addAttribute("", productService.getProductWithId(id));
        return "warehouse/warehouseDetail";
    }

}

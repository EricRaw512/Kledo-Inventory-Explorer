package com.example.kledo.productkledo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kledo.productkledo.entity.Product;
import com.example.kledo.productkledo.service.ProductService;
import com.example.kledo.productkledo.service.WarehouseService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    
    private final ProductService productService;
    private final WarehouseService warehouseService;

    @GetMapping("/all")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProductsWithTotalQty());
        return "product/productList";
    }
    
    @GetMapping("/{id}")
    public String showWarehouse(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductWithId(id));
        model.addAttribute("warehouses", warehouseService.getAllWarehousesAndQtyByProductId(id));
        return "product/productDetail";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id); // Set the product ID from path variable (ensure it's not overwritten)
        productService.updateProduct(product);
        return "redirect:/product/id"; // Redirect to product list page after update
    }
}

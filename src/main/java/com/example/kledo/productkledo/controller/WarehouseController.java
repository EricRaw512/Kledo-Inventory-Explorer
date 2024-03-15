package com.example.kledo.productkledo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kledo.productkledo.service.ProductService;
import com.example.kledo.productkledo.service.WarehouseService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("warehouse")
public class WarehouseController {
    
    private final WarehouseService warehouseService;
    private final ProductService productService;

    @GetMapping("/all")
    public String showAllWarehouses(Model model) {
        model.addAttribute("warehouses", warehouseService.getAllWarehouse());
        return "warehouse/warehouseList";
    }
    
    @GetMapping("/{id}")
    public String showWarehouse(@PathVariable("id") int id, Model model) {
        model.addAttribute("products", productService.getAllProductsWithWarehouseId(id));
        model.addAttribute("warehouse", warehouseService.getWarehouseWithId(id));
        return "warehouse/warehouseDetail";
    }
    
}

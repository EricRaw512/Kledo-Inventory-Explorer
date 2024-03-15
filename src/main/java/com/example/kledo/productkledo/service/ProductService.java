package com.example.kledo.productkledo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.kledo.productkledo.dao.ProductRepository;
import com.example.kledo.productkledo.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsWithWarehouseId(int id) {
        return productRepository.findProductsByWarehouseId(id);
    }

}

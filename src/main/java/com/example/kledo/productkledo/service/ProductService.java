package com.example.kledo.productkledo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.kledo.productkledo.dao.ProductRepository;
import com.example.kledo.productkledo.dto.ProductDTO;
import com.example.kledo.productkledo.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProductsWithTotalQty() {
        List<Object[]> results = productRepository.findAllProductsWithTotalQty();
        List<ProductDTO> productQtyDTOs = new ArrayList<>();
        for (Object[] result : results) {
            String name = (String) result[0];
            String photo = (String) result[1];
            int totalQty = (int) result[2];
            productQtyDTOs.add(new ProductDTO(name, photo, totalQty));
        }

        return productQtyDTOs;
    }

    public List<ProductDTO> getAllProductsWithWarehouseId(int id) {
        List<Object[]> results = productRepository.findProductsByWarehouseId(id);
        List<ProductDTO> productQtyDTOs = new ArrayList<>();
        for (Object[] result : results) {
            String name = (String) result[0];
            String photo = (String) result[1];
            int qty = (int) result[2];
            productQtyDTOs.add(new ProductDTO(name, photo, qty));
        }

        return productQtyDTOs;
    }

    public Product getProductWithId(int id) {
        return productRepository.findById(id).orElse(null);
    }

}

package com.example.kledo.productkledo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.kledo.productkledo.dao.ProductWarehouseRepository;
import com.example.kledo.productkledo.entity.Product;
import com.example.kledo.productkledo.entity.ProductWarehouse;
import com.example.kledo.productkledo.entity.Warehouse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductWarehouseService {
    
    private final ProductWarehouseRepository productWarehouseRepository;

    public void deleteAllProductWarehouseByWarehouseId(int warehouseId) {
        productWarehouseRepository.deleteByWarehouseId(warehouseId);
    }

    public void associateProductWithWarehouse(List<Product> products, Warehouse warehouse, Map<Integer, Integer> productIdToQuantity) {
        for (Product product : products) {
            ProductWarehouse productWarehouse = new ProductWarehouse();
            productWarehouse.setProduct(product);
            productWarehouse.setWarehouse(warehouse);
            productWarehouse.setQty(productIdToQuantity.get(product.getId()));
            productWarehouseRepository.save(productWarehouse);
        }
    }
}

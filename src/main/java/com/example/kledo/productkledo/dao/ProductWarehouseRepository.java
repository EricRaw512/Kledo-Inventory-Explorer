package com.example.kledo.productkledo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kledo.productkledo.entity.ProductWarehouse;

public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouse, Integer>{
    
    void deleteByWarehouseId(int warehouseId);
}

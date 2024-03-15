package com.example.kledo.productkledo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kledo.productkledo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("SELECT p FROM Warehouse w JOIN w.products p WHERE w.id = :id")
    List<Product> findProductsByWarehouseId(@Param("id") int id);
}

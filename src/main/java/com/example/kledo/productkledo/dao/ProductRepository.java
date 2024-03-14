package com.example.kledo.productkledo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kledo.productkledo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    
}

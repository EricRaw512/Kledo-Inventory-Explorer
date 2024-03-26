package com.example.kledo.productkledo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kledo.productkledo.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

    @Query("SELECT pw.id, pw.warehouse, pw.qty FROM ProductWarehouse pw JOIN pw.product p WHERE p.id = :productId")
    List<Object[]> findAllWarehousesAndQtyByProductId(@Param("productId") int productId);

} 

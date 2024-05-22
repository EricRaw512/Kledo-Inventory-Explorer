package com.example.kledo.productkledo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kledo.productkledo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("SELECT p.id, p.name, p.photo, pw.qty, p.priceList FROM Product p JOIN p.productWarehouses pw JOIN pw.warehouse w WHERE w.id = :warehouseId")
    List<Object[]> findProductsByWarehouseId(@Param("warehouseId") int id);

    @Query("SELECT p.id, p.name, p.photo, SUM(pw.qty), p.priceList FROM Product p JOIN p.productWarehouses pw GROUP BY p")
    List<Object[]> findAllProductsWithTotalQty();
}

package com.example.kledo.productkledo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.kledo.productkledo.dao.WarehouseRepository;
import com.example.kledo.productkledo.entity.Warehouse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    
    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseWithId(int id) {
        return warehouseRepository.findById(id).orElse(null);
    }

}

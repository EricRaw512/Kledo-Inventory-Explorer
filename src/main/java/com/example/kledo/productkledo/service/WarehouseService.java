package com.example.kledo.productkledo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.kledo.productkledo.dao.WarehouseRepository;
import com.example.kledo.productkledo.dto.WarehouseDTO;
import com.example.kledo.productkledo.entity.Warehouse;
import com.example.kledo.productkledo.model.WarehouseResponse;

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

    public List<WarehouseDTO> getAllWarehousesAndQtyByProductId(int productId) {
        List<Object[]> results = warehouseRepository.findAllWarehousesAndQtyByProductId(productId);
        List<WarehouseDTO> warehouseQtyDTOs = new ArrayList<>();
        for (Object[] result : results) {
            Warehouse warehouse = (Warehouse) result[0];
            int qty = ((Number) result[1]).intValue();
            warehouseQtyDTOs.add(new WarehouseDTO(warehouse, qty));
        }

        return warehouseQtyDTOs;
    }

    public void updateWarehouses(WarehouseResponse fetchedWarehouses) {
        List<Warehouse> warehouses = fetchedWarehouses.getData().getData().stream()
            .map(this::convertToEntityWarehouse)
            .collect(Collectors.toList());
        
        for (Warehouse warehouse : warehouses) {
            warehouseRepository.save(warehouse);
        }
    }

    public Warehouse convertToEntityWarehouse(WarehouseResponse.WarehouseData.Warehouse apiWarehouse) {
        Warehouse warehouses = new Warehouse();
        warehouses.setId(apiWarehouse.getId());
        warehouses.setName(apiWarehouse.getName());
        warehouses.setQty((int) apiWarehouse.getQty());
        return warehouses;
    }

}

package com.example.kledo.productkledo.dto;

import com.example.kledo.productkledo.entity.Warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseDTO {
    Warehouse warehouse;
    private int qty;
}

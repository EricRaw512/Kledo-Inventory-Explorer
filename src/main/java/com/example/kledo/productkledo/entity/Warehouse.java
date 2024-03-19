package com.example.kledo.productkledo.entity;

import java.util.Set;

import com.example.kledo.productkledo.model.WarehouseResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse extends BaseEntity{
    
    @Column(name = "name")
    private String name;

    @Column(name = "qty")
    private int qty;
    
    @OneToMany(mappedBy = "warehouse")
    private Set<ProductWarehouse> productWarehouses;
}

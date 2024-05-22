package com.example.kledo.productkledo.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntityWithoutGenerated{

    private String name;
    private String photo;
    private int priceList;

    @Transient
    private int categoryId;

    @OneToMany(mappedBy = "product")
    private Set<ProductWarehouse> productWarehouses;
}

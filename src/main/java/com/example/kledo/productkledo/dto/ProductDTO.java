package com.example.kledo.productkledo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private int id;
    private String name;
    private String photo;
    private int qty;
}

package com.example.kledo.productkledo.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseEntityWithoutGenerated {
    
    @Id
    private int id;
}

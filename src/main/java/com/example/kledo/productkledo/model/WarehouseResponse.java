package com.example.kledo.productkledo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WarehouseResponse {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private WarehouseData data;

    @Data
    public static class WarehouseData {
        @JsonProperty("data")
        private List<Warehouse> data;

        @Data
        public static class Warehouse {
            @JsonProperty("id")
            private int id;

            @JsonProperty("name")
            private String name;

            @JsonProperty("qty")
            private double qty;
        }
    }
}

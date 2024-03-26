package com.example.kledo.productkledo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductsResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private WarehouseData data;

    @Data
    public static class WarehouseData {
        
        @JsonProperty("products")
        private Products products;

        @Data
        public static class Products {
            @JsonProperty("data")
            private List<ProductInWarehouse> data;


            @Data
            public static class ProductInWarehouse {
                @JsonProperty("product_id")
                private int productId;
        
                @JsonProperty("qty")
                private int qty;
        
                @JsonProperty("name")
                private String name;
        
                @JsonProperty("photo")
                private String photo;
            }
        }
    }
}

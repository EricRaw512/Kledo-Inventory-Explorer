package com.example.kledo.productkledo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductsOnlyResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private Products data;

    @Data
    public static class Products {
        @JsonProperty("data")
        private List<AllProducts> data;

        @Data
        public static class AllProducts {
            @JsonProperty("id")
            private int id;
    
            @JsonProperty("name")
            private String name;
    
            @JsonProperty("photo")
            private String photo;
        }
    }
}

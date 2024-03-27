package com.example.kledo.productkledo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BearerResponse {
    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("data")
    private Data data;

    public static class Data {
        @JsonProperty("data")
        private InnerData innerData;
        
        public InnerData getInnerData() {
            return innerData;
        }

        public static class InnerData {
            @JsonProperty("access_token")
            private String accessToken;

            public String getAccessToken() {
                return accessToken;
            }
        }





    }

    // Method to directly access the access_token
    public String getAccessToken() {
        return data != null && data.getInnerData() != null ? data.getInnerData().getAccessToken() : null;
    }
}

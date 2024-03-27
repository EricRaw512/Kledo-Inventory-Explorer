package com.example.kledo.productkledo.service;

import com.example.kledo.productkledo.model.BearerRequest;
import com.example.kledo.productkledo.model.BearerResponse;
import com.example.kledo.productkledo.model.ProductsOnlyResponse;
import com.example.kledo.productkledo.model.ProductsResponse;
import com.example.kledo.productkledo.model.WarehouseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate; 

@RequiredArgsConstructor
@Service
public class APIService {

    private final RestTemplate restTemplate;
    
    public BearerResponse fetchBearerToken(BearerRequest bearerRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<BearerRequest> request = new HttpEntity<>(bearerRequest, headers);

        try {
            ResponseEntity<BearerResponse> response = restTemplate.exchange(
                    "https://cvciptakaryafurindo.api.kledo.com/api/v1/authentication/singleLogin",
                    HttpMethod.POST,
                    request,
                    BearerResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getResponseBodyAsString() != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(e.getResponseBodyAsString(), BearerResponse.class);
                } catch (JsonProcessingException ex) {
                    System.out.println("Error parsing response body: " + ex.getMessage());
                }
            }

            return null; 
        }
    }

    public WarehouseResponse fetchWarehousesFromApi(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<WarehouseResponse> response = restTemplate.exchange(
                    "https://cvciptakaryafurindo.api.kledo.com/api/v1/finance/warehouses",
                    HttpMethod.GET,
                    entity,
                    WarehouseResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getResponseBodyAsString() != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(e.getResponseBodyAsString(), WarehouseResponse.class);
                } catch (JsonProcessingException ex) {
                    System.out.println("Error parsing response body: " + ex.getMessage());
                }
            }
            
            return null; 
        }
    }

    public ProductsOnlyResponse fetchProductsFromApi(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken); 
        HttpEntity<String> entity = new HttpEntity<>(headers);
    
        try {
            ResponseEntity<ProductsOnlyResponse> response = restTemplate.exchange(
                    "https://cvciptakaryafurindo.api.kledo.com/api/v1/finance/products?per_page=10000",
                    HttpMethod.GET, 
                    entity,
                    ProductsOnlyResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getResponseBodyAsString() != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(e.getResponseBodyAsString(), ProductsOnlyResponse.class);
                } catch (JsonProcessingException ex) {
                    System.out.println("Error parsing response body: " + ex.getMessage());
                }
            }
            
            return null; 
        }
    }

    public ProductsResponse fetchProductsFroWarehousemApi(String bearerToken, int warehouseId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken); 
        HttpEntity<String> entity = new HttpEntity<>(headers);
    
        try {
            ResponseEntity<ProductsResponse> response = restTemplate.exchange(
                    "https://cvciptakaryafurindo.api.kledo.com/api/v1/finance/warehouses/" + warehouseId + "?per_page=10000",
                    HttpMethod.GET, 
                    entity,
                    ProductsResponse.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getResponseBodyAsString() != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(e.getResponseBodyAsString(), ProductsResponse.class);
                } catch (JsonProcessingException ex) {
                    System.out.println("Error parsing response body: " + ex.getMessage());
                }
            }
            
            return null; 
        }
    }
}

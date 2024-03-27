package com.example.kledo.productkledo.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kledo.productkledo.entity.Product;
import com.example.kledo.productkledo.entity.Warehouse;
import com.example.kledo.productkledo.model.BearerRequest;
import com.example.kledo.productkledo.model.BearerResponse;
import com.example.kledo.productkledo.model.ProductsOnlyResponse;
import com.example.kledo.productkledo.model.ProductsResponse;
import com.example.kledo.productkledo.model.ProductsResponse.WarehouseData.Products.ProductInWarehouse;
import com.example.kledo.productkledo.model.WarehouseResponse;
import com.example.kledo.productkledo.service.APIService;
import com.example.kledo.productkledo.service.ProductService;
import com.example.kledo.productkledo.service.ProductWarehouseService;
import com.example.kledo.productkledo.service.WarehouseService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("update")
public class UpdateController {
    
    private final APIService apiService;
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final ProductWarehouseService productWarehouseService;

    @GetMapping("/")
    public String showUpdateForm(Model model) {
        model.addAttribute("bearerRequest", new BearerRequest());
        return "update/updateForm";
    }

    @PostMapping("/")
    public String processUpdateProductWarehouse(BearerRequest bearerRequest, Model model) {
        //get The bearer Token
        BearerResponse bearerResponse = apiService.fetchBearerToken(bearerRequest);
        if (!bearerResponse.isSuccess()) {
            model.addAttribute("message", "Username or Password is wrong");
            return "update/updateForm";
        }

        String bearerToken = bearerResponse.getAccessToken();
        //get The wareHouse Data
        WarehouseResponse fetchedWarehouses = apiService.fetchWarehousesFromApi(bearerToken);
        if (!fetchedWarehouses.isSuccess()) {
            model.addAttribute("message", "Failed to fetch Warehouse Data");
            return "update/updateForm";
        }

        //Update warehouse Product...
        List<Warehouse> warehouses = updateWarehouse(fetchedWarehouses);
        //Get Product Per Warehouses
        Set<Integer> addedProduct = new HashSet<>();
        for (Warehouse warehouse : warehouses) {
            int curWarehouseId = warehouse.getId();

            //Get The Product
            ProductsResponse fetchedProduct = apiService.fetchProductsFroWarehousemApi(bearerToken, curWarehouseId);
            if (!fetchedProduct.isSuccess()) {
                model.addAttribute("message", "Failed to fetch Product Data From" + curWarehouseId);
                return "update/updateForm";
            }

            //Delete All Product Associate With The Current Warehouse Id First
            productWarehouseService.deleteAllProductWarehouseByWarehouseId(curWarehouseId);

            //Update The Product
            List<Product> products = updateProductPerWarehouseWithSet(fetchedProduct, addedProduct);

            //Get Qty Per Product Map<ProductId, Qty>
            Map<Integer, Integer> productIdToQuantity = productIdToQuantity(fetchedProduct);

            //Update The Product_Warehouse
            productWarehouseService.associateProductWithWarehouse(products, warehouse, productIdToQuantity);
        }
        
        model.addAttribute("message", "Update Success");
        return "update/updateForm";
    }

    @PostMapping("/product")
    public String processProductUpdate(BearerRequest bearerRequest, Model model) {
        //get The bearer Token
        BearerResponse bearerResponse = apiService.fetchBearerToken(bearerRequest);
        if (!bearerResponse.isSuccess()) {
            model.addAttribute("message", "Username or Password is wrong");
            return "update/updateForm";
        }

        String bearerToken = bearerResponse.getAccessToken();
        //get The Product Data
        ProductsOnlyResponse fetchesProduct = apiService.fetchProductsFromApi(bearerToken);
        if (!fetchesProduct.isSuccess()) {
            model.addAttribute("message", "Failed to fetch product Data");
            return "update/updateForm";
        }

        updateProduct(fetchesProduct);
        model.addAttribute("message", "Update Success");
        return "update/updateForm";
    }
    
    private Map<Integer, Integer> productIdToQuantity(ProductsResponse fetchedProduct) {
        Map<Integer, Integer> productIdToQuantity = new HashMap<>();
        for (ProductInWarehouse productInWarehouse : fetchedProduct.getData().getProducts().getData()) {
            productIdToQuantity.put(productInWarehouse.getProductId(), productInWarehouse.getQty());
        }

        return productIdToQuantity;
    }

    private List<Product> updateProductPerWarehouseWithSet(ProductsResponse fetchedProduct,Set<Integer> addedProduct) {
        List<Product> products = fetchedProduct.getData().getProducts().getData().stream()
            .map(productService::convertToEntityProduct)
            .collect(Collectors.toList());

        productService.updateProductsWithSet(products, addedProduct);
        return products;
    }

    private void updateProduct(ProductsOnlyResponse fetchedProduct) {
        List<Product> products = fetchedProduct.getData().getData().stream()
            .map(productService::convertToEntityProduct)
            .collect(Collectors.toList());

        productService.updateProducts(products);
    }

    private List<Warehouse> updateWarehouse(WarehouseResponse fetchedWarehouses) {
        List<Warehouse> warehouses = fetchedWarehouses.getData().getData().stream()
            .map(warehouseService::convertToEntityWarehouse)
            .collect(Collectors.toList());
            
        warehouseService.updateWarehouses(warehouses);
        return warehouses;
    } 
    
}

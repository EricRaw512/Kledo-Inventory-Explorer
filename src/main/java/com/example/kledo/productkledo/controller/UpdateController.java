package com.example.kledo.productkledo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kledo.productkledo.entity.Warehouse;
import com.example.kledo.productkledo.model.BearerRequest;
import com.example.kledo.productkledo.model.BearerResponse;
import com.example.kledo.productkledo.model.WarehouseResponse;
import com.example.kledo.productkledo.service.APIService;
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

    @GetMapping("/")
    public String showUpdateForm(Model model) {
        model.addAttribute("bearerRequest", new BearerRequest());
        return "update/updateForm";
    }

    @PostMapping("/")
    public String processUpdate(BearerRequest bearerRequest, Model model) {
        BearerResponse bearerResponse = apiService.callApi(bearerRequest);

        if (!bearerResponse.isSuccess()) {
            model.addAttribute("message", bearerResponse.getMessage());
            return "update/updateForm";
        }

        String bearerToken = bearerResponse.getAccessToken();
        WarehouseResponse fetchedWarehouses = apiService.fetchWarehousesFromApi(bearerToken);

        if (!fetchedWarehouses.isSuccess()) {
            model.addAttribute("message", "Failed to fetch Warehouse Data");
            return "update/updateForm";
        }
        
        warehouseService.updateWarehouses(fetchedWarehouses);
        //Update warehouse Product...

        model.addAttribute("message", "Update Success");
        return "update/updateForm";
    }
    
    
}

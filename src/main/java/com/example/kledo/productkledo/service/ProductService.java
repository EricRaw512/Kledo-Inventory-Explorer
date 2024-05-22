package com.example.kledo.productkledo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.kledo.productkledo.dao.ProductRepository;
import com.example.kledo.productkledo.dto.ProductDTO;
import com.example.kledo.productkledo.entity.Product;
import com.example.kledo.productkledo.model.ProductsOnlyResponse;
import com.example.kledo.productkledo.model.ProductsResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProductsWithTotalQty() {
        List<Object[]> results = productRepository.findAllProductsWithTotalQty();
        List<ProductDTO> productQtyDTOs = new ArrayList<>();
        for (Object[] result : results) {
            int productId = (int) result[0];
            String name = (String) result[1];
            String photo = (String) result[2];
            Long value = (long) result[3];
            int totalQty = value.intValue();
            int priceList = (int) result[4];
            productQtyDTOs.add(new ProductDTO(productId, name, photo, totalQty, priceList));
        }

        return productQtyDTOs;
    }

    public List<ProductDTO> getAllProductsWithWarehouseId(int id) {
        List<Object[]> results = productRepository.findProductsByWarehouseId(id);
        List<ProductDTO> productQtyDTOs = new ArrayList<>();
        for (Object[] result : results) {
            int productId = (int) result[0];
            String name = (String) result[1];
            String photo = (String) result[2];
            int totalQty = (int) result[3];
            int priceList = (int) result[4];
            productQtyDTOs.add(new ProductDTO(productId, name, photo, totalQty, priceList));
        }

        return productQtyDTOs;
    }

    public Product getProductWithId(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product convertToEntityProduct(ProductsResponse.WarehouseData.Products.ProductInWarehouse apiProduct) {
        Product product = new Product();
        product.setId(apiProduct.getProductId());
        product.setName(apiProduct.getName());
        product.setPhoto(apiProduct.getPhoto());
        return product;
    }

    public Product convertToEntityProduct(ProductsOnlyResponse.Products.AllProducts apiProduct) {
        Product product = new Product();
        product.setId(apiProduct.getId());
        product.setName(apiProduct.getName());
        product.setPhoto(apiProduct.getPhoto());
        product.setPriceList(apiProduct.getPrice());
        product.setCategoryId(apiProduct.getCategoryId());
        return product;
    }

    public void createOrUpdateProduct(Product product) {
        Product productExist = getProductWithId(product.getId());
        if (productExist != null && productExist.getPriceList() != 0) {
            product.setPriceList(productExist.getPriceList());
        } else {
            double newPriceList = 0;
            if (product.getCategoryId() == 1 && product.getPriceList() > 1_200_000) newPriceList = product.getPriceList() * 2.5;
            else newPriceList = product.getPriceList() * 3;
            
            product.setPriceList((int) Math.ceil(newPriceList / 1000.0) * 1000);
        }

        productRepository.save(product);
    }

    public void updateProducts(List<Product> products) {
        products.forEach(this::createOrUpdateProduct);
    }

    public void updateProductsWithSet(List<Product> products, Set<Integer> addedProduct) {
        for (Product product : products) {
            if (!addedProduct.add(product.getId())) continue;
            createOrUpdateProduct(product);
        }
    }

    public void updateProductPricelist(Product product) {
        productRepository.save(product);
    }

}

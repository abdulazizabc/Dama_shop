package com.example.dama_shop.service;

import com.example.dama_shop.dto.requests.ProductRequest;
import com.example.dama_shop.dto.response.ProductResponse;
import com.example.dama_shop.model.Product;
import com.example.dama_shop.model.enums.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductResponse> getProducts();
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductsByCategory(ProductCategory category);
    ProductResponse addProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);

}


package com.example.dama_shop.service;

import com.example.dama_shop.model.Product;
import com.example.dama_shop.model.enums.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getProducts();
    public Optional<Product> getProductById(Long id);
    public List<Product> getProductsByCategory(ProductCategory category);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Long id);

}

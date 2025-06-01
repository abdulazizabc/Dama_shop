package com.example.dama_shop.controller;

import com.example.dama_shop.model.Product;
import com.example.dama_shop.model.enums.ProductCategory;
import com.example.dama_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("Product/{productId}")
    public Optional<Product> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory( @PathVariable ProductCategory category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/save-product")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update-product")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete-product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

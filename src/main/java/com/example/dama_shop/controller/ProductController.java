package com.example.dama_shop.controller;

import com.example.dama_shop.dto.requests.ProductRequest;
import com.example.dama_shop.dto.response.ProductResponse;
import com.example.dama_shop.model.enums.ProductCategory;
import com.example.dama_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;


    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable ProductCategory category) {
        List<ProductResponse> responses = productService.getProductsByCategory(category);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/save/product")
    public ResponseEntity<ProductResponse> addProduct( @Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.addProduct(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
         ProductResponse response = productService.updateProduct(id,request);
         return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}

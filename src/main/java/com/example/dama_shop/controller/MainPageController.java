package com.example.dama_shop.controller;

import com.example.dama_shop.model.Product;
import com.example.dama_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final ProductService productService;

    @GetMapping()
    public List<Product> getProducts() {
        return productService.getProducts();
    }

}

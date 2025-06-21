package com.example.dama_shop.controller;

import com.example.dama_shop.dto.response.ProductResponse;
import com.example.dama_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> responses = productService.getProducts();
        return ResponseEntity.ok(responses);
    }

}

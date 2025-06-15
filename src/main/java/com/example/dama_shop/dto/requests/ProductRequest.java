package com.example.dama_shop.dto.requests;

import com.example.dama_shop.model.enums.ProductCategory;
import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private ProductCategory category;
}


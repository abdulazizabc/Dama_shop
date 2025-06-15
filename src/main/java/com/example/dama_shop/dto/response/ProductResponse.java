package com.example.dama_shop.dto.response;

import com.example.dama_shop.model.enums.ProductCategory;
import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private ProductCategory category;
}

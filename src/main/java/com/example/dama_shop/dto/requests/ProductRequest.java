package com.example.dama_shop.dto.requests;

import com.example.dama_shop.model.enums.ProductCategory;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProductRequest {

    @NotBlank
    private String productName;

    @NotBlank
    @Size(min = 1, max = 1000)
    private String productDescription;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1_000_000_000.0")
    private double productPrice;

    @Min(0)
    private int productQuantity;

    @NotNull
    private ProductCategory category;
}


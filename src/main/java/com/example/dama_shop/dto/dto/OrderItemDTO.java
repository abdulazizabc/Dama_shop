package com.example.dama_shop.dto.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OrderItemDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String productName;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1_000_000_000.0")
    private double productPrice;

    @Min(1)
    private int quantity;

}

package com.example.dama_shop.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CartItemDTO {

    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;

}

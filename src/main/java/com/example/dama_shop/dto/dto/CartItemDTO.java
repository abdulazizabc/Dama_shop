package com.example.dama_shop.dto.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private String productName;

    private double productPrice;

    private int quantity;

    private double totalPrice;
}

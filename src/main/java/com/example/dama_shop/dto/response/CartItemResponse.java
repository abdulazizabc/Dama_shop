package com.example.dama_shop.dto.response;

import lombok.Data;

@Data
public class CartItemResponse {

    private String productName;

    private double productPrice;

    private int quantity;

    private double totalPrice;
}

package com.example.dama_shop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartResponse {

    private List<CartItemResponse> items;

    private double totalPrice;
}

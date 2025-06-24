package com.example.dama_shop.dto.response;

import com.example.dama_shop.dto.dto.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartResponse {

    private List<CartItemDTO> items;

    private double totalPrice;
}

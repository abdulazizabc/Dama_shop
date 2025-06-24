package com.example.dama_shop.service;

import com.example.dama_shop.dto.dto.CartItemDTO;
import com.example.dama_shop.dto.response.CartResponse;

public interface CartService {

    void addToCart(CartItemDTO cartItemDTO);

    void removeFromCart(Long productId);

    CartResponse getCartItems();

    void clearCart();
}


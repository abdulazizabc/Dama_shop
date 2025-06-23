package com.example.dama_shop.controller;

import com.example.dama_shop.dto.CartItemDTO;
import com.example.dama_shop.dto.response.CartResponse;
import com.example.dama_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/carts")
public class CartItemController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Void> addToCart(@RequestBody @Valid CartItemDTO cartItemDTO) {
        cartService.addToCart(cartItemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCartItems() {
        return ResponseEntity.ok().body(cartService.getCartItems());
    }

}

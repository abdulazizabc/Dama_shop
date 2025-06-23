package com.example.dama_shop.service.impl;

import com.example.dama_shop.dto.CartItemDTO;
import com.example.dama_shop.dto.mapping.CartItemMapper;
import com.example.dama_shop.dto.response.CartItemResponse;
import com.example.dama_shop.dto.response.CartResponse;
import com.example.dama_shop.exception.NotFoundException;
import com.example.dama_shop.model.CartItem;
import com.example.dama_shop.model.Product;
import com.example.dama_shop.model.User;
import com.example.dama_shop.repository.CartItemRepository;
import com.example.dama_shop.repository.ProductRepository;
import com.example.dama_shop.service.AuthService;
import com.example.dama_shop.service.CartService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final AuthService authService;
    private final CartItemMapper cartItemMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void addToCart(CartItemDTO cartItemDTO) {
        User user = authService.getCurrentUser();

        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> {
                    log.error("Product not found");
                    return new NotFoundException("Product not found");
                });

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product).orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDTO.getQuantity());
        } else {
            cartItem = new CartItem(null, user, product, cartItemDTO.getQuantity());
        }

        cartItemRepository.save(cartItem);

    }

    @Override
    @Transactional
    public void removeFromCart(Long productId) {

        User user = authService.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product not found");
                    return new NotFoundException("Product not found");
                });

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> {
                    log.error("Cart item not found");
                    return new NotFoundException("Cart item not found");
                });

        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartResponse getCartItems() {
        User user = authService.getCurrentUser();

        List<CartItem> cartItems = cartItemRepository.findCartItemsByUser(user);
        List<CartItemResponse> items = cartItems
                .stream()
                .map(cartItemMapper::toCartItemResponse)
                .toList();

        double totalPrice = items.stream().mapToDouble(CartItemResponse::getTotalPrice).sum();
        return new CartResponse(items, totalPrice);
    }

    @Override
    public void clearCart() {
        User user = authService.getCurrentUser();
        cartItemRepository.deleteByUser(user);
    }
}

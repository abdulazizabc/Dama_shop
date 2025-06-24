package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.dto.CartItemDTO;
import com.example.dama_shop.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    public CartItemDTO toCartItemDTO(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemDTO cartItemDTO = new CartItemDTO();

        cartItemDTO.setQuantity( cartItem.getQuantity() );
        cartItemDTO.setProductName( cartItem.getProduct().getProductName() );
        cartItemDTO.setProductPrice(cartItem.getProduct().getProductPrice());
        cartItemDTO.setTotalPrice(cartItem.getProduct().getProductPrice() * cartItem.getQuantity());

        return cartItemDTO;
    }
}

package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.CartItemDTO;
import com.example.dama_shop.dto.response.CartItemResponse;
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
        cartItemDTO.setProductId( cartItem.getProduct().getId() );

        return cartItemDTO;
    }


    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponse cartItemResponse = new CartItemResponse();

        cartItemResponse.setQuantity( cartItem.getQuantity() );
        cartItemResponse.setProductName( cartItem.getProduct().getProductName() );
        cartItemResponse.setProductPrice(cartItem.getProduct().getProductPrice());
        cartItemResponse.setTotalPrice(cartItem.getProduct().getProductPrice() * cartItem.getQuantity());

        return cartItemResponse;
    }
}

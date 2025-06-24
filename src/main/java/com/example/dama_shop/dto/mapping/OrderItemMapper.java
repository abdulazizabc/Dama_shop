package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.dto.OrderItemDTO;
import com.example.dama_shop.model.CartItem;
import com.example.dama_shop.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

    OrderItemDTO toOrderItemDTO(CartItem cartItem);

    OrderItem toOrderItem(OrderItemDTO orderItemDTO);


}

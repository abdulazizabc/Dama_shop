package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderName(order.getOrderName());
        dto.setPrice((int)order.getPrice());
        dto.setStatus(order.getStatus().toString());
        dto.setUser_id(order.getUser().getId());
        return dto;
    }

    public Order toEntity(OrderDTO dto, User user) {
        Order order = new Order();
        order.setOrderName(dto.getOrderName());
        order.setPrice(dto.getPrice());
        order.setStatus(OrderStatus.valueOf(dto.getStatus()));
        order.setUser(user);
        return order;
    }
}

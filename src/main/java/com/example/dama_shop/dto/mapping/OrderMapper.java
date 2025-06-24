package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.dto.OrderDTO;
import com.example.dama_shop.dto.dto.OrderItemDTO;
import com.example.dama_shop.dto.response.OrderResponse;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.OrderItem;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        if ( order.getStatus() != null ) {
            orderDTO.setStatus( order.getStatus().name() );
        }

        List<OrderItemDTO> items = order.getOrderItems()
                .stream()
                .map(orderItemMapper::toOrderItemDTO)
                .toList();

        double totalPrice = 0.0;

        for ( OrderItemDTO item : items ) {
            totalPrice += item.getProductPrice() * item.getQuantity();
        }

        orderDTO.setOrderId( order.getId() );
        orderDTO.setStatus( order.getStatus().name() );
        orderDTO.setItems( items );
        orderDTO.setTotalPrice( totalPrice );

        return orderDTO;
    }

    public Order toEntity(OrderDTO dto, User user) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();
        List<OrderItem> items = dto.getItems().stream()
                .map(orderItemMapper::toOrderItem)
                .toList();

        order.setId(dto.getOrderId());
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderItems(items);
        order.setTotalPrice(dto.getTotalPrice());
        order.setStatus(OrderStatus.valueOf(dto.getStatus()));

        return order;
    }

    public OrderResponse toOrderResponse(Order savedOrder) {
        OrderResponse orderResponse = new OrderResponse();
        List<OrderItemDTO> orderItems = savedOrder.getOrderItems().stream()
                        .map(orderItemMapper::toOrderItemDTO)
                        .toList();

        orderResponse.setOrderId(savedOrder.getId());
        orderResponse.setItems(orderItems);
        orderResponse.setTotalPrice(savedOrder.getTotalPrice());
        orderResponse.setOrderDate(LocalDateTime.now());

        return orderResponse;

    }
}

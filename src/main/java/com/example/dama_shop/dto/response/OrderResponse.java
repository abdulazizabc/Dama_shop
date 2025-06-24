package com.example.dama_shop.dto.response;

import com.example.dama_shop.dto.dto.OrderItemDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long orderId;

    private String orderStatus;

    private List<OrderItemDTO> items;

    private double totalPrice;

    private LocalDateTime orderDate;
}

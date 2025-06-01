package com.example.dama_shop.service;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.model.enums.OrderStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDTO createOrder(OrderDTO dto);
    List<OrderDTO> getOrdersByUserId(Long userId);
    OrderDTO updateOrderStatusById(Long orderId, OrderStatus status);
    void deleteOrder(Long id);
    Optional<OrderDTO> getOrderById(Long id);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> myOrders();


}

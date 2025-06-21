package com.example.dama_shop.service;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.model.enums.OrderStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDTO createOrder(OrderDTO dto);
    OrderDTO updateOrderStatusById(Long orderId, OrderStatus status);
    void deleteOrder(Long id) throws AccessDeniedException;
    OrderDTO getMyOrderById(Long id);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getMyOrders();
}

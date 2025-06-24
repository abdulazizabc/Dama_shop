package com.example.dama_shop.service;

import com.example.dama_shop.dto.requests.OrderRequest;
import com.example.dama_shop.dto.response.OrderResponse;
import com.example.dama_shop.model.Order;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);
    List<Order> getOrders();

}

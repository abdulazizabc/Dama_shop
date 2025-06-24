package com.example.dama_shop.service.impl;

import com.example.dama_shop.dto.mapping.OrderItemMapper;
import com.example.dama_shop.dto.mapping.OrderMapper;
import com.example.dama_shop.dto.requests.OrderRequest;
import com.example.dama_shop.dto.response.OrderResponse;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.OrderItem;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.OrderStatus;
import com.example.dama_shop.repository.OrderRepository;
import com.example.dama_shop.service.AuthService;
import com.example.dama_shop.service.CartService;
import com.example.dama_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderItemMapper  orderItemMapper;
    private final OrderMapper  orderMapper;
    private final AuthService authService;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        double totalPrice = 0;
        User user =  authService.getCurrentUser();
        LocalDateTime createdAt = LocalDateTime.now();
        OrderStatus status = OrderStatus.CONFIRMED;

        List<OrderItem> orderItems = request.getItems().stream()
                .map(orderItemMapper::toOrderItem)
                .toList();

        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getProductPrice() * orderItem.getQuantity();
        }

        Order savedOrder = new Order(null,user,createdAt,status,totalPrice,orderItems);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);
        }

        orderRepository.save(savedOrder);
        log.info("Order with id ={} created", savedOrder.getId());

        cartService.clearCart();
        log.info("cleared cart");
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}

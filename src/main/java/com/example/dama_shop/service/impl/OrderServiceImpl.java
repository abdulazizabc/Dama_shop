package com.example.dama_shop.service.impl;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.dto.mapping.OrderMapper;
import com.example.dama_shop.dto.mapping.UserMapper;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.OrderStatus;
import com.example.dama_shop.repository.OrderRepository;
import com.example.dama_shop.repository.UserRepository;
import com.example.dama_shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserMapper userMapper;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderMapper orderMapper;

    public OrderDTO createOrder(OrderDTO dto) {
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderMapper.toEntity(dto, user);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderStatusById(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toDto);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> myOrders() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long user_id = user.getId();

        List<Order> orders = orderRepository.findByUserId(user_id);

        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());


    }

}


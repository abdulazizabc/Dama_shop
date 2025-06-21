package com.example.dama_shop.service.impl;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.dto.mapping.OrderMapper;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.OrderStatus;
import com.example.dama_shop.repository.OrderRepository;
import com.example.dama_shop.repository.UserRepository;
import com.example.dama_shop.service.AuthService;
import com.example.dama_shop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final AuthService authService;
    private final OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderMapper orderMapper;

    private Long safeGetCurrentUserId() {
        try {
            return authService.getCurrentUserId();
        } catch (AccessDeniedException e) {
            log.warn("Access denied");
            throw new RuntimeException("Access denied");
        }
    }

    private Order findOrderOrThrow(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.warn("Order with id={} not found", orderId);
                    return new RuntimeException("Order not found");
                });
    }


    @Transactional
    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        User user = userRepository.findById(authService.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        log.info("Create Order of user: {}", user.getUsername());

        Order order = orderMapper.toEntity(dto,user);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public OrderDTO updateOrderStatusById(Long orderId, OrderStatus status) {
        Order order = findOrderOrThrow(orderId);

        OrderStatus oldStatus = order.getStatus();
        order.setStatus(status);

        log.info("Update status of order with id={}: from {} to {}", orderId, oldStatus, status);

        Order updated = orderRepository.save(order);
        return orderMapper.toDto(updated);
    }


    @Override
    public void deleteOrder(Long id){

        Long currentUserId = safeGetCurrentUserId();


        Order order = findOrderOrThrow(id);

        if (!Objects.equals(order.getUser().getId(), currentUserId)){
            log.warn("Order with id={} not owned by user with id {}", order.getId(), currentUserId);
            throw new RuntimeException("Order not owned by user with id " + order.getId());
        }

        orderRepository.delete(order);
        log.info("User with id = {} deleted order with id={}", currentUserId, id);
    }

    @Override
    public OrderDTO getMyOrderById(Long id) {
        Order order = findOrderOrThrow(id);

        if (!Objects.equals(order.getUser().getId(), safeGetCurrentUserId())){
            log.warn("Order with id = {} not owned by user with id {}", order.getId(), safeGetCurrentUserId());
            throw new RuntimeException("Order not owned by user with id " + order.getId());
        }
        return orderMapper.toDto(order);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDTO> getAllOrders() {
        log.info("Get All Orders for Admin");
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getMyOrders() {
        log.info("Get My Orders for every user");

        Long currentUserId = safeGetCurrentUserId();


        if (!userRepository.existsById(currentUserId)) {
            log.warn("User with id {} not found", currentUserId);
            throw new RuntimeException("User not found");
        }


        List<Order> orders = orderRepository.findByUserId(currentUserId);

        return orders
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());

    }

}


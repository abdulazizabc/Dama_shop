package com.example.dama_shop.controller;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.enums.OrderStatus;
import com.example.dama_shop.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto) {
        OrderDTO order = orderService.createOrder(dto);
        return ResponseEntity.ok(order);
    }


    @PutMapping("/updateOrderStatus/{orderId}")
    public ResponseEntity<OrderDTO> updateOrderStatusById(@PathVariable Long orderId,
                                                          @RequestBody OrderStatus status){
        return ResponseEntity.ok(orderService.updateOrderStatusById(orderId, status));
    }

    @DeleteMapping("/delete-order/{orderId}")
    public void deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/orderById/{id}")
    public ResponseEntity<Optional<OrderDTO>> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/my-orders")
    public List<OrderDTO> myOrders(){
        return orderService.myOrders();
    }

}


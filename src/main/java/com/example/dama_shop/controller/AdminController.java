package com.example.dama_shop.controller;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.model.User;
import com.example.dama_shop.service.OrderService;
import com.example.dama_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/save-user")
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/update-user-info")
    public ResponseEntity<UserDTO> updateUserInfo(Long userId,@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUserInfoByAdmin(userId,userDTO));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

}

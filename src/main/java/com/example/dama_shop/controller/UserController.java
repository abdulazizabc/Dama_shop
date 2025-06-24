package com.example.dama_shop.controller;

import com.example.dama_shop.dto.dto.UserDTO;
import com.example.dama_shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }


    @PutMapping("/update-my-profile")
    public ResponseEntity<UserDTO> updateMyProfile(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateMyProfile(userDTO));
    }

}

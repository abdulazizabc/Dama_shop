package com.example.dama_shop.controller;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.mapping.UserMapper;
import com.example.dama_shop.dto.requests.LoginRequest;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.dto.response.JwtResponse;
import com.example.dama_shop.model.User;
import com.example.dama_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        String jwt = userService.verify(request);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

}

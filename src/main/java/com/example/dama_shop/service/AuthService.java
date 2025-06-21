package com.example.dama_shop.service;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.requests.LoginRequest;
import com.example.dama_shop.dto.requests.UserRequestDTO;

public interface AuthService {

    String verify(LoginRequest request);
    Long getCurrentUserId();
    UserDTO register(UserRequestDTO request);
}

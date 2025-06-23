package com.example.dama_shop.service;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.requests.LoginRequest;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.model.User;

public interface AuthService {

    String verify(LoginRequest request);
    Long getCurrentUserId();
    User getCurrentUser();
    UserDTO register(UserRequestDTO request);
}

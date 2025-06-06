package com.example.dama_shop.service;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.requests.LoginRequest;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserDTO> findAll();
    UserDTO save(UserRequestDTO request);
    User save(User user);
    Optional<UserDTO> findById(Long id);
    Optional<UserDTO> findByUsername(String username);
    void deleteById(Long id);
    UserDTO updateUserInfo(Long id,UserDTO userDTO);

    String verify(LoginRequest request);
}

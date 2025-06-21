package com.example.dama_shop.service;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.requests.LoginRequest;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.model.User;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    UserDTO updateMyProfile(UserDTO userDTO);

    //ADMIN
    User save(User user);
    void deleteById(Long id);
    UserDTO updateUserInfoByAdmin(Long userId,UserDTO dto);
}

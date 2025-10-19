package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.dto.UserDTO;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setAge(user.getAge());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);
        return dto;
    }

    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAge(dto.getAge());

        if (dto.getRole() != null) {
            try {
                user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalide role: " + dto.getRole());
            }
        }

        return user;
    }
}

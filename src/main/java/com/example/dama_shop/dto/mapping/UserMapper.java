package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final OrderMapper orderMapper;

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.username = user.getUsername();
        userDTO.age = user.getAge();
        userDTO.role = String.valueOf(user.getRole());

        if (user.getOrders() != null) {

            List<OrderDTO> orderDTOs = user.getOrders().stream()
                    .map(orderMapper::toDto)
                    .collect(Collectors.toList());

            userDTO.setOrders(orderDTOs);
        }

        return userDTO;
    }

    public User toEntity(UserRequestDTO dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setAge(dto.getAge());
        user.setPassword(dto.getPassword());
        user.setRole(Role.valueOf(dto.getRole()));


        if (dto.getOrders() != null) {
            user.setOrders(dto.getOrders()
                    .stream()
                    .map(orderDTO -> orderMapper.toEntity(orderDTO, user))
                    .collect(Collectors.toList()));
        }

        return user;
    }
}

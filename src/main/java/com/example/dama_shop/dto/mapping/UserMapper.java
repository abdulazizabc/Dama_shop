package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.UserDTO;
import com.example.dama_shop.dto.requests.UserRequestDTO;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "orders", ignore = true)
    UserDTO toDTO(User user);

    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role", qualifiedByName = "stringToRole")
    @Mapping(target = "orders", ignore = true)
    User toEntity(UserRequestDTO dto);

    @Named("stringToRole")
    static Role stringToRole(String role) {
        return Role.valueOf(role);
    }

}

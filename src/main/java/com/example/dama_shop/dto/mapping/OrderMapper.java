package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.OrderStatus;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "user_id")
    OrderDTO toDto(Order order);

    @Mapping(target = "user", ignore = true)
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    Order toEntity(OrderDTO dto, @Context User user);

    @Named("stringToStatus")
    static OrderStatus stringToStatus(String status) {
        return OrderStatus.valueOf(status);
    }
}

package com.example.dama_shop.dto.mapping;

import com.example.dama_shop.dto.OrderDTO;
import com.example.dama_shop.model.Order;
import com.example.dama_shop.model.User;
import com.example.dama_shop.model.enums.OrderStatus;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "user_id")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    OrderDTO toDto(Order order);

    @Mapping(target = "user", ignore = true)
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    Order toEntity(OrderDTO dto, @Context User user);


    @Named("statusToString")
    static String statusToString(OrderStatus status) {
        return status.name();
    }

    @Named("stringToStatus")
    static OrderStatus stringToStatus(String status) {
        return OrderStatus.valueOf(status);
    }

    default List<Order> toEntityList(List<OrderDTO> dtos, @Context User user) {
        return dtos.stream()
                .map(dto -> toEntity(dto, user))
                .collect(Collectors.toList());
    }
}

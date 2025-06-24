package com.example.dama_shop.dto.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull
    private Long orderId;

    @NotBlank
    private String status;

    List<OrderItemDTO> items;

    @DecimalMin(value = "0.0")
    private double totalPrice;

}

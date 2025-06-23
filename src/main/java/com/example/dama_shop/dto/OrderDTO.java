package com.example.dama_shop.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderDTO {

    @NotNull
    private Long user_id;

    @NotBlank
    private String orderName;

    @Min(value = 0)
    @Max(value = 1_000_000_000)
    private double price;

    @NotBlank
    private String status;

}

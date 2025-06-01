package com.example.dama_shop.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private Long user_id;
    private String orderName;
    private int price;
    private String status;

}

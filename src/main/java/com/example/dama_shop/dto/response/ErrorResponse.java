package com.example.dama_shop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private int status;
    private String timestamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

}

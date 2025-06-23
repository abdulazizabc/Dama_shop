package com.example.dama_shop.dto.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ErrorResponse {

    @NotBlank
    private String message;

    private int status;
    private String timestamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

}

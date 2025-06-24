package com.example.dama_shop.exception.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
public class ErrorResponse {

    @NotBlank
    private String message;

    private int status;
    private String timestamp = Instant.now().toString();

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

}

package com.example.dama_shop.dto.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}

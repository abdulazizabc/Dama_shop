package com.example.dama_shop.dto.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDTO {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Min(value = 0, message = "Age must be >= 0")
    @Max(value = 120, message = "Age must be <= 120")
    private int age;

    @NotBlank(message = "Role must not be blank")
    private String role;
}

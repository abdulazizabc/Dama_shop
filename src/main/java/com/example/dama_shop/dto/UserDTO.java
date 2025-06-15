package com.example.dama_shop.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class UserDTO {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Min(value = 0, message = "Age must be >= 0")
    @Max(value = 120, message = "Age must be <= 120")
    private int age;

    @NotBlank(message = "Role must not be blank")
    private String role;

    @Valid
    private List<@NotNull OrderDTO> orders;
}

package com.example.dama_shop.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import java.util.List;

@Data
public class UserDTO {

    @NotBlank
    public String username;

    @Min(0)
    @Max(120)
    public int age;

    @NotBlank
    public String role;

    List<OrderDTO> orders;
}

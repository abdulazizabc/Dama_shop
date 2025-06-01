package com.example.dama_shop.dto.requests;

import com.example.dama_shop.dto.OrderDTO;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserRequestDTO {
    @NotBlank
    private String username;

    @Min(0)
    @Max(120)
    private int age;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    private String role;

    List<OrderDTO> orders;

}

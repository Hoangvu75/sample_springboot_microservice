package com.cnweb.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email address is empty")
    private String email;

    @NotBlank(message = "Password is empty")
    private String password;
}

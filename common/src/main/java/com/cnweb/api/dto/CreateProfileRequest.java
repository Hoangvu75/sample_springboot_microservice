package com.cnweb.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProfileRequest {
    @NotBlank(message = "Name is empty")
    private String name;
}

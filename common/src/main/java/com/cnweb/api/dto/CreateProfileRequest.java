package com.cnweb.api.dto;

import com.cnweb.api.models.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Date;

@Data
public class CreateProfileRequest {
    @NotBlank(message = "Name is empty")
    private String name;

    @NotBlank(message = "Phone number is empty")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a valid 10-digit string")
    private String phone;

    @NotBlank(message = "Address is empty")
    private String address;

    private Date dateOfBirth;

    private Gender gender;
}

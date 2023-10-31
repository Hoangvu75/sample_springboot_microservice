package com.cnweb.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateProfileRequest {
    @NotBlank(message = "Name is empty")
    private String name;

    @NotBlank(message = "Phone number is empty")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a valid 10-digit string")
    private String phone;

    @NotBlank(message = "Address is empty")
    private String address;

    @Min(value = 1900, message = "Birth year must be greater than or equal to 1900")
    @Max(value = 2023, message = "Birth year must be less than or equal to 2023")
    private Integer birthYear;

    @Min(value = 1, message = "Birth month must be greater than or equal to 1")
    @Max(value = 12, message = "Birth month must be less than or equal to 12")
    private Integer birthMonth;

    @Min(value = 1, message = "Birth day must be greater than or equal to 1")
    @Max(value = 31, message = "Birth day must be less than or equal to 31")
    private Integer birthDay;

    @NotBlank(message = "Gender is empty")
    @Pattern(regexp = "^(MALE|FEMALE)$", message = "Gender must be either MALE or FEMALE")
    private String gender;
}

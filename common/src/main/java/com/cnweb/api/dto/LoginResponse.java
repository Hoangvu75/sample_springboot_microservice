package com.cnweb.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Boolean isSuccess;
    private Object message;
    private String accessToken;
}

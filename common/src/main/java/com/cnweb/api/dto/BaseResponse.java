package com.cnweb.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {
    private Boolean isSuccess;
    private Object message;
    private Object data;
}

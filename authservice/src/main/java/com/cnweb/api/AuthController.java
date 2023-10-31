package com.cnweb.api;

import com.cnweb.api.dto.BaseResponse;
import com.cnweb.api.dto.LoginRequest;
import com.cnweb.api.dto.RegisterRequest;
import com.cnweb.api.utils.CustomValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult
    ) {
        BaseResponse errorResponse = BaseResponse.builder().isSuccess(false).build();
        try {
            List<String> validateRequest = CustomValidation.checkForValidation(bindingResult);
            if (validateRequest != null) {
                errorResponse.setMessage(validateRequest);
                return ResponseEntity.internalServerError().body(errorResponse);
            }
            return ResponseEntity.ok(authService.register(request));
        } catch (Throwable error) {
            errorResponse.setMessage(error.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @Valid @RequestBody LoginRequest request,
            BindingResult bindingResult
    ) {
        BaseResponse errorResponse = BaseResponse.builder().isSuccess(false).build();
        try {
            List<String> validateRequest = CustomValidation.checkForValidation(bindingResult);
            if (validateRequest != null) {
                errorResponse.setMessage(validateRequest);
                return ResponseEntity.internalServerError().body(errorResponse);
            }
            return ResponseEntity.ok(authService.login(request));
        } catch (Throwable error) {
            errorResponse.setMessage(error.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}

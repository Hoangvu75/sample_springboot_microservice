package com.cnweb.api;

import com.cnweb.api.dto.BaseResponse;
import com.cnweb.api.dto.CreateProfileRequest;
import com.cnweb.api.utils.CustomValidation;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(
            @Valid @RequestBody CreateProfileRequest request,
            BindingResult bindingResult,
            HttpServletRequest httpServletRequest
    ) {
        BaseResponse errorResponse = BaseResponse.builder().isSuccess(false).build();
        try {
            List<String> validateRequest = CustomValidation.checkForValidation(bindingResult);
            if (validateRequest != null) {
                errorResponse.setMessage(validateRequest);
                return ResponseEntity.status(500).body(errorResponse);
            }
            String authorization = httpServletRequest.getHeader("Authorization").substring(7);
            return ResponseEntity.ok(profileService.create(request, authorization));
        } catch (Throwable error) {
            errorResponse.setMessage(error.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}

package com.cnweb.api;

import com.cnweb.api.config.security.JwtService;
import com.cnweb.api.dto.BaseResponse;
import com.cnweb.api.dto.CreateProfileRequest;
import com.cnweb.api.models.Profile;
import com.cnweb.api.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public BaseResponse create(CreateProfileRequest request, String authorization) {
        String accountId = JwtService.extractAccountId(authorization);
        Profile profile = Profile.builder()
                .accountId(accountId)
                .name(request.getName())
                .build();
        profileRepository.save(profile);
        return BaseResponse.builder()
                .isSuccess(true)
                .message("Created profile successfully.")
                .data(profile)
                .build();
    }
}

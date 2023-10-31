package com.cnweb.api;

import com.cnweb.api.config.security.JwtService;
import com.cnweb.api.dto.BaseResponse;
import com.cnweb.api.dto.CreateProfileRequest;
import com.cnweb.api.entities.Profile;
import com.cnweb.api.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public BaseResponse create(CreateProfileRequest request, String accessToken) {
        String accountId = JwtService.extractAccountId(accessToken);
        Profile profile = Profile.builder()
                .accountId(accountId)
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender().toString())
                .build();
        profileRepository.save(profile);
        return BaseResponse.builder()
                .isSuccess(true)
                .message("Created profile successfully.")
                .data(profile)
                .build();
    }

    public BaseResponse getProfile(String accessToken) {
        String accountId = JwtService.extractAccountId(accessToken);
        Profile profile = profileRepository.findByAccountId(accountId).orElseThrow();
        return BaseResponse.builder()
                .isSuccess(true)
                .message("Get profile successfully.")
                .data(profile)
                .build();
    }
}

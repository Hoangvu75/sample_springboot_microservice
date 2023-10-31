package com.cnweb.api;

import com.cnweb.api.config.security.JwtService;
import com.cnweb.api.dto.BaseResponse;
import com.cnweb.api.dto.CreateProfileRequest;
import com.cnweb.api.entities.Profile;
import com.cnweb.api.models.DateOfBirth;
import com.cnweb.api.models.Gender;
import com.cnweb.api.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public BaseResponse create(CreateProfileRequest request, String accessToken) {
        String accountId = JwtService.extractAccountId(accessToken);
        Optional<Profile> profile = profileRepository.findByAccountId(accountId);
        if (profile.isEmpty()) {
            Profile newProfile = Profile.builder()
                    .accountId(accountId)
                    .name(request.getName())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .dateOfBirth(
                            DateOfBirth.builder()
                                    .year(request.getBirthYear())
                                    .month(request.getBirthMonth())
                                    .day(request.getBirthDay())
                                    .build()
                    )
                    .gender(Gender.valueOf(request.getGender()))
                    .build();
            profileRepository.save(newProfile);
            return BaseResponse.builder()
                    .isSuccess(true)
                    .message("Created profile successfully.")
                    .data(newProfile)
                    .build();
        } else {
            return BaseResponse.builder()
                    .isSuccess(false)
                    .message("Profile already created for this account.")
                    .data(null)
                    .build();
        }
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

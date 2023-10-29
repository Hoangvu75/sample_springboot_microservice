package com.cnweb.api;

import com.cnweb.api.config.security.JwtService;
import com.cnweb.api.dto.BaseResponse;
import com.cnweb.api.dto.LoginRequest;
import com.cnweb.api.dto.LoginResponse;
import com.cnweb.api.dto.RegisterRequest;
import com.cnweb.api.models.Account;
import com.cnweb.api.models.Role;
import com.cnweb.api.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.cnweb.api.config.security"})
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Object register(RegisterRequest request) {
        Account account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        accountRepository.save(account);
        return BaseResponse.builder()
                .isSuccess(true)
                .message("Registered successfully.")
                .data(account)
                .build();
    }

    public Object login(LoginRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail()).orElseThrow();
        var authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authToken);
        String jwtToken = jwtService.generateToken(account);
        return LoginResponse.builder()
                .isSuccess(true)
                .message("Logged in successfully.")
                .accessToken(jwtToken)
                .build();
    }
}

package com.rms.recruitment.services;

import com.rms.recruitment.dto.AuthRequest;
import com.rms.recruitment.dto.AuthResponse;
import com.rms.recruitment.dto.RegisterRequest;
import com.rms.recruitment.models.Accounts;
import com.rms.recruitment.repositories.AccountRepository;
import com.rms.recruitment.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Accounts account = Accounts.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status((byte) 1)
                .employeeId(request.getEmployeeId())
                .build();

        accountRepository.save(account);

        String token = jwtUtil.generateToken(account.getEmail());
        return new AuthResponse(token, account.getEmail(), "User registered successfully");
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponse(token, request.getEmail(), "Login successful");
    }
}

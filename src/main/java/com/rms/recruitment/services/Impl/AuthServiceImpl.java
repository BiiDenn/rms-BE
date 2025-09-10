package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.request.AuthRequest;
import com.rms.recruitment.dto.response.AuthResponse;
import com.rms.recruitment.dto.request.RegisterRequest;
import com.rms.recruitment.models.Accounts;
import com.rms.recruitment.repositories.AccountRepository;
import com.rms.recruitment.security.JwtUtil;
import com.rms.recruitment.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
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

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponse(token, request.getEmail(), "Login successful");
    }
}

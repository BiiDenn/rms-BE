package com.rms.recruitment.services;

import com.rms.recruitment.dto.AuthRequest;
import com.rms.recruitment.dto.AuthResponse;
import com.rms.recruitment.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}
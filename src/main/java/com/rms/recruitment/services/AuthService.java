package com.rms.recruitment.services;

import com.rms.recruitment.dto.request.AuthRequest;
import com.rms.recruitment.dto.response.AuthResponse;
import com.rms.recruitment.dto.request.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}
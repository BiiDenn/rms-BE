package com.rms.recruitment.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    @Operation(summary = "Public endpoint", description = "This endpoint is accessible without authentication")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("This is a public endpoint - no authentication required");
    }

    @GetMapping("/protected")
    @Operation(summary = "Protected endpoint", description = "This endpoint requires JWT authentication", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<String> protectedEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok("This is a protected endpoint. Authenticated user: " + username);
    }

    @GetMapping("/admin")
    @Operation(summary = "Admin endpoint", description = "This endpoint requires admin role", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<String> adminEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok("This is an admin endpoint. Authenticated user: " + username);
    }
}

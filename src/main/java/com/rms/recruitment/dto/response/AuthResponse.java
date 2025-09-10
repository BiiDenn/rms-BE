package com.rms.recruitment.dto.response;

public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private String message;

    // Constructors
    public AuthResponse() {
    }

    public AuthResponse(String token, String email, String message) {
        this.token = token;
        this.email = email;
        this.message = message;
    }

    public AuthResponse(String token, String type, String email, String message) {
        this.token = token;
        this.type = type;
        this.email = email;
        this.message = message;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private String type = "Bearer";
        private String email;
        private String message;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token, type, email, message);
        }
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

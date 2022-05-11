package com.example.fs.response;

import lombok.Data;

@Data
public class AuthResponse {

    String message;
    Long userId;
    String accessToken, refreshToken;

}

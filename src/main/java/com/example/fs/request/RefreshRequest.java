package com.example.fs.request;

import lombok.Data;

@Data
public class RefreshRequest {
    Long userId;

   String refreshToken;
}

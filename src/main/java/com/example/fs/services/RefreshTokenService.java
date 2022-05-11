package com.example.fs.services;

import com.example.fs.entities.RefreshToken;
import com.example.fs.entities.User;
import com.example.fs.repos.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository = refreshTokenRepository;
    }


    public String createRefreshToken(User user){
        RefreshToken token =  refreshTokenRepository.findByUserId(user.getId());
        if (token==null){
            token = new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(token);
        return token.getToken();
    }
    public boolean isRefreshExpired(RefreshToken refreshToken){
        return refreshToken.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUserId(Long userId){
        return  refreshTokenRepository.findByUserId(userId);
    }


}

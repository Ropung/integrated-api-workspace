package com.joara.auth.refresh.repository;

import com.joara.auth.refresh.entity.RefreshTokenEntity;
import com.joara.auth.usecase.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class RefreshTokenPersistence implements RefreshTokenRepository {
    private final RefreshTokenRedisRepository redisRepository;

    @Override
    public boolean save(String email, String oldRefreshToken, String refreshToken, String userAgent) {
        RefreshTokenEntity entity = RefreshTokenEntity.builder()
                .email(email)
                .refreshToken(refreshToken)
                .userAgent(userAgent)
                // 30 Days
                .ttl(2_592_000_000L)
                .build();

        redisRepository.deleteById(oldRefreshToken);
        redisRepository.save(entity);
        return true;
    }

    @Override
    public boolean existsByEmailAndRefreshToken(String email, String refreshToken) {
        return redisRepository.existsByEmailAndRefreshToken(email, refreshToken);
    }

    @Override
    public boolean deleteByRefreshToken(String refreshToken) {
        redisRepository.deleteById(refreshToken);
        return true;
    }
}

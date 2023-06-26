package com.joara.auth.usecase;

public interface RefreshTokenRepository {
    boolean save(String email, String oldRefreshToken, String refreshToken, String userAgent);
    boolean existsByEmailAndRefreshToken(String email, String refreshToken);
    boolean deleteByRefreshToken(String refreshToken);
}

package com.joara.auth.refresh.repository;

import com.joara.auth.refresh.entity.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenEntity, String> {
    boolean existsByEmailAndRefreshToken(String email, String refreshToken);
}

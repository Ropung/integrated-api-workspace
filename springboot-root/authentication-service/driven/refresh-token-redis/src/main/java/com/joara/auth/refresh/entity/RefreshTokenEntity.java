package com.joara.auth.refresh.entity;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Builder
@ToString
@EqualsAndHashCode
@RedisHash("refreshToken")
public class RefreshTokenEntity {
    @Id
    @Indexed
    public String refreshToken;
    @Indexed
    public String email;
    public String userAgent;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    public Long ttl;
}

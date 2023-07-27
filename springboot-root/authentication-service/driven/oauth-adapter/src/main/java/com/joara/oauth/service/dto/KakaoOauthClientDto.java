package com.joara.oauth.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoOauthClientDto() {
    public record KakaoOauthResponseClientDto(
            @JsonProperty("token_type")
            String tokenType,
            @JsonProperty("access_token")
            String oauthAccessToken,
            @JsonProperty("id_token")
            String openIdToken,
            @JsonProperty("expires_in")
            Long expiresIn,
            @JsonProperty("refresh_token")
            String oauthRefreshToken,
            @JsonProperty("refresh_token_expires_in")
            Long refreshTokenExpiresIn,
            @JsonProperty("scope")
            @JsonInclude(Include.NON_EMPTY)
            String scope
    ) {}
}

package com.joara.auth.clients.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.OffsetDateTime;

public record OauthClientDto() {

    @Builder
    public record OauthRequestClientDto(
            @JsonProperty("redirect_uri")
            String redirectUri,
            @JsonProperty("code")
            String code,
            @JsonProperty("client_secret")
            @JsonInclude(Include.NON_EMPTY)
            String clientSecret
    ) {}

    @Builder
    public record OauthResponse(
            @JsonProperty("token_type")
            String tokenType,
            @JsonProperty("access_token")
            String oauthAccessToken,
            @JsonProperty("id_token")
            OpenIdPayload openIdPayload,
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

    @Builder
    public record OpenIdPayload(
            @JsonProperty("iss")
            String issuer,
            @JsonProperty("sub")
            Long openId,
            @JsonProperty("auth_time")
            OffsetDateTime authTime,
            @JsonProperty("nickname")
            String nickname,
            @JsonProperty("picture")
            String picture,
            @JsonProperty("email")
            String email
    ) {}
}

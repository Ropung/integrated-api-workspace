package com.joara.auth.usecase.dto;

import lombok.Builder;

@Builder
public record AuthenticationTokens(
        String accessToken,
        String refreshToken
) {
}

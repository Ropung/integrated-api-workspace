package com.joara.auth.usecase;

import com.joara.auth.usecase.dto.AuthenticationTokens;

import javax.servlet.http.HttpServletRequest;

public interface RefreshUseCase {
    AuthenticationTokens refresh(String email, String oldRefreshToken);
    AuthenticationTokens refresh(HttpServletRequest request);
}

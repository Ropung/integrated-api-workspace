package com.joara.auth.usecase;

import com.joara.auth.usecase.dto.AuthenticationTokens;
import com.joara.auth.usecase.dto.MemberLoginDto.EmailAndPasswordLoginRequestDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginUseCase {
	AuthenticationTokens login(EmailAndPasswordLoginRequestDto dto, String oldRefreshToken);

	AuthenticationTokens login(EmailAndPasswordLoginRequestDto dto, HttpServletRequest request);
}

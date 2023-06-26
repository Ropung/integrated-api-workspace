package com.joara.web;

import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.domain.model.type.MemberTier;
import com.joara.auth.usecase.LoginUseCase;
import com.joara.auth.usecase.RefreshTokenRepository;
import com.joara.auth.usecase.RefreshUseCase;
import com.joara.auth.usecase.SignUpUseCase;
import com.joara.auth.usecase.dto.AuthenticationTokens;
import com.joara.auth.usecase.dto.MemberLoginDto.EmailAndPasswordLoginRequestDto;
import com.joara.auth.usecase.dto.MemberLoginDto.MemberLoginResponseDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpRequestDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public final class AuthenticationApi {

	private final SignUpUseCase signUpUseCase;
	private final LoginUseCase loginUseCase;
	private final RefreshUseCase refreshUseCase;
	private final RefreshTokenRepository refreshTokenRepository;

	@PostMapping("/sign-up")
	public MemberSignUpResponseDto signUp(
			@RequestBody @Valid MemberSignUpRequestDto dto
	) {
		AccountStatus status = AccountStatus.ACTIVE;
		CertType certType = CertType.SELF;
		MemberTier tier = MemberTier.BRONZE;
		return signUpUseCase.signUp(dto, status, certType, tier);
	}

	@PostMapping("/login")
	public MemberLoginResponseDto login(
			@RequestBody @Valid EmailAndPasswordLoginRequestDto body,
			HttpServletRequest request,
			HttpServletResponse response
	) {
		AuthenticationTokens tokens = loginUseCase.login(body, request);
		Cookie cookie = new Cookie("refresh_token", tokens.refreshToken());
		cookie.setMaxAge(2_592_000); // seconds
		cookie.setDomain(""); // localhost
		cookie.setPath("/");
		cookie.setHttpOnly(true);

		response.addCookie(cookie);

		return MemberLoginResponseDto.builder()
				.token(tokens.accessToken())
				.build();
	}

	@PostMapping("/refresh")
	public MemberLoginResponseDto refresh(
			HttpServletRequest request,
			HttpServletResponse response
	) {
		AuthenticationTokens tokens = refreshUseCase.refresh(request);
		Cookie cookie = new Cookie("refresh_token", tokens.refreshToken());
		cookie.setMaxAge(2_592_000); // seconds
		cookie.setDomain(""); // localhost
		cookie.setPath("/");
		cookie.setHttpOnly(true);

		response.addCookie(cookie);

		return MemberLoginResponseDto.builder()
				.token(tokens.accessToken())
				.build();
	}
}

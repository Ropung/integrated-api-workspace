package com.joara.auth.service;

import com.joara.auth.domain.model.Member;
import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.domain.model.type.MemberTier;
import com.joara.auth.exception.AuthenticationErrorCode;
import com.joara.auth.exception.AuthenticationException;
import com.joara.auth.repository.MemberCommandRepository;
import com.joara.auth.repository.MemberQueryRepository;
import com.joara.auth.usecase.LoginUseCase;
import com.joara.auth.usecase.RefreshTokenRepository;
import com.joara.auth.usecase.RefreshUseCase;
import com.joara.auth.usecase.SignUpUseCase;
import com.joara.auth.usecase.dto.AuthenticationTokens;
import com.joara.auth.usecase.dto.MemberLoginDto.EmailAndPasswordLoginRequestDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpRequestDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpResponseDto;
import com.joara.auth.usecase.mapper.MemberDtoMapper;
import com.joara.auth.utils.jwt.JwtProvider;
import com.joara.auth.utils.random.StringSecureRandom;
import com.joara.jwt.util.JwtParser;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.Arrays;


@Service
@RequiredArgsConstructor
public class AuthenticationService
		implements SignUpUseCase, LoginUseCase, RefreshUseCase {
	private final MemberCommandRepository memberCommandRepository;
	private final MemberQueryRepository memberQueryRepository;
	private final MemberDtoMapper mapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final JwtParser jwtParser;
	private final AuthenticationManager authenticationManager;
	private final StringSecureRandom stringSecureRandom;
	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public boolean signUp(Member member) {
		// 중복 체크
		boolean exists = memberQueryRepository.existsMemberByEmail(member.email);
		if (exists) throw new IllegalArgumentException("이미 존재하는 이메일입니다.");

		// 비밀번호 인코딩
		member.password = passwordEncoder.encode(member.password);

		memberCommandRepository.save(member);
		return true;
	}

	@Override
	public MemberSignUpResponseDto signUp(MemberSignUpRequestDto dto, AccountStatus status, CertType certType, MemberTier tier) {
		// DTO와 기타 입력값들을 -> Member 도메인으로 변환.
		OffsetDateTime createdAt = ServerTime.now();
		Member member = mapper.from(dto, status ,createdAt, certType, tier);

		// 변환한 도메인을 signUp(Member member) 메서드에게 인계 + 그 결과를 반환 DTO로 변환.
		return MemberSignUpResponseDto.builder()
				.success(signUp(member)) // 인계
				.build();
	}

	@Override
	public AuthenticationTokens login(EmailAndPasswordLoginRequestDto dto, String oldRefreshToken) {
		// 스프링 시큐리티 Authentication Manager를 통한 인증
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.rawPassword());
			authenticationManager.authenticate(authenticationToken);
		} catch (org.springframework.security.core.AuthenticationException e) {
			throw AuthenticationErrorCode.MISMATCHED.defaultException(e);
		}

		return saveAuthTokens(dto.email(), oldRefreshToken);
	}

	@Override
	public AuthenticationTokens login(EmailAndPasswordLoginRequestDto dto, HttpServletRequest request) {
		try {
			Cookie cookie = Arrays.stream(request.getCookies())
					.filter((eachCookie) -> "refresh_token".equals(eachCookie.getName()))
					.findAny()
					.orElseThrow(AuthenticationErrorCode.UNAUTHORIZED::defaultException);

			String oldRefreshToken = cookie.getValue();
			return login(dto, oldRefreshToken);
		} catch (NullPointerException | AuthenticationException e) {
			return login(dto, "");
		}
	}

	@Override
	public AuthenticationTokens refresh(String email, String oldRefreshToken) {
		if (!refreshTokenRepository.existsByEmailAndRefreshToken(email, oldRefreshToken)) {
			throw AuthenticationErrorCode.INVALID_TOKENS.defaultException();
		}

		return saveAuthTokens(email, oldRefreshToken);
	}

	@Override
	public AuthenticationTokens refresh(HttpServletRequest request) {
		String email;

		try {
			email = jwtParser.withRequest(request).subject();
		} catch (AuthenticationCredentialsNotFoundException e) {
			throw AuthenticationErrorCode.UNAUTHORIZED.defaultException(e);
		}

		try {
			Cookie cookie = Arrays.stream(request.getCookies())
					.filter((eachCookie) -> "refresh_token".equals(eachCookie.getName()))
					.findAny()
					.orElseThrow(AuthenticationErrorCode.UNAUTHORIZED::defaultException);

			String oldRefreshToken = cookie.getValue();
			return refresh(email, oldRefreshToken);
		} catch (NullPointerException e) {
			return refresh(email, "");
		}
	}

	private AuthenticationTokens saveAuthTokens(String email) {
		return saveAuthTokens(email, "");
	}

	private AuthenticationTokens saveAuthTokens(String email, String oldRefreshToken) {
		// Acceptable
		String nickname = memberQueryRepository.findByEmail(email)
				.orElseThrow()
				.nickname;;
		String accessToken = jwtProvider.generateAsUser(email, nickname);
		String refreshToken = stringSecureRandom.next();

		// To DB
		refreshTokenRepository.save(email, oldRefreshToken, refreshToken, "");

		// To user
		return AuthenticationTokens.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
	}
}

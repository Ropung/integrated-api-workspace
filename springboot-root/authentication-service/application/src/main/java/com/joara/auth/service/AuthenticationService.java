package com.joara.auth.service;

import com.joara.auth.domain.model.Member;
import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.exception.AuthenticationErrorCode;
import com.joara.auth.repository.MemberCommandRepository;
import com.joara.auth.repository.MemberQueryRepository;
import com.joara.auth.usecase.LoginUseCase;
import com.joara.auth.usecase.SignUpUseCase;
import com.joara.auth.usecase.dto.MemberLoginDto.MemberLoginRequestDto;
import com.joara.auth.usecase.dto.MemberLoginDto.MemberLoginResponseDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpRequestDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpResponseDto;
import com.joara.auth.usecase.mapper.MemberDtoMapper;
import com.joara.auth.utils.jwt.JwtProvider;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements SignUpUseCase, LoginUseCase {
	private final MemberCommandRepository memberCommandRepository;
	private final MemberQueryRepository memberQueryRepository;
	private final MemberDtoMapper mapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public boolean signUp(Member member) {
		boolean isValid = memberQueryRepository.existsMemberByEmail(member.email);
		if (isValid) throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
		member.password = passwordEncoder.encode(member.password);
		
		memberCommandRepository.save(member);
		return true;
	}
	@Override
	public MemberSignUpResponseDto signUp(MemberSignUpRequestDto dto, AccountStatus status, CertType certType) {
		
		OffsetDateTime createdAt = ServerTime.now();
		Member member = mapper.from(dto, status ,createdAt, certType);
		
		return MemberSignUpResponseDto.builder()
				.success(signUp(member)) // 인계
				.build();
	}
	
	@Override
	public MemberLoginResponseDto login(MemberLoginRequestDto dto) {
		// 스프링 시큐리티 Authentication Manager를 통한 인증
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.rawPassword());
			authenticationManager.authenticate(authenticationToken);
		} catch (org.springframework.security.core.AuthenticationException e) {
			throw AuthenticationErrorCode.MISMATCHED.defaultException(e);
		}
		
		String token = jwtProvider.generate(dto.email());
		
		return MemberLoginResponseDto.builder()
				.token(token)
				.build();
	}
}

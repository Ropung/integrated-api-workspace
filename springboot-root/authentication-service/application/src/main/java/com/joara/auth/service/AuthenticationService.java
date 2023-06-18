package com.joara.auth.service;

import com.joara.auth.domain.model.Member;
import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.domain.model.type.MemberTier;
import com.joara.auth.exception.AuthenticationErrorCode;
import com.joara.auth.repository.MemberCommandRepository;
import com.joara.auth.repository.MemberQueryRepository;
import com.joara.auth.usecase.LoginUseCase;
import com.joara.auth.usecase.SignUpUseCase;
import com.joara.auth.usecase.dto.MemberLoginDto.EmailAndPasswordLoginRequestDto;
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
	public MemberLoginResponseDto login(EmailAndPasswordLoginRequestDto dto) {
		// 스프링 시큐리티 Authentication Manager를 통한 인증
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.rawPassword());
			authenticationManager.authenticate(authenticationToken);
		} catch (org.springframework.security.core.AuthenticationException e) {
			throw AuthenticationErrorCode.MISMATCHED.defaultException(e);
		}

		// Acceptable
		String nickname = memberQueryRepository.findByEmail(dto.email())
				.orElseThrow()
				.nickname;;
		String token = jwtProvider.generateAsUser(dto.email(), nickname);

		return MemberLoginResponseDto.builder()
				.token(token)
				.build();
	}
}

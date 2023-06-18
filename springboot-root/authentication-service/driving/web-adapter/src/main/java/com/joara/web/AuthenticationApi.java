package com.joara.web;

import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.domain.model.type.MemberTier;
import com.joara.auth.usecase.LoginUseCase;
import com.joara.auth.usecase.SignUpUseCase;
import com.joara.auth.usecase.dto.MemberLoginDto.EmailAndPasswordLoginRequestDto;
import com.joara.auth.usecase.dto.MemberLoginDto.MemberLoginResponseDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpRequestDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public final class AuthenticationApi {

	private final SignUpUseCase signUpUseCase;
	private final LoginUseCase loginUseCase;

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
			@RequestBody EmailAndPasswordLoginRequestDto body
	) {
		return loginUseCase.login(body);
	}
}

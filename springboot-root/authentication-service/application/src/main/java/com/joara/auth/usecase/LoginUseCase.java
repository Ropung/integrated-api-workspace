package com.joara.auth.usecase;

import com.joara.auth.usecase.dto.MemberLoginDto.EmailAndPasswordLoginRequestDto;
import com.joara.auth.usecase.dto.MemberLoginDto.MemberLoginResponseDto;

public interface LoginUseCase {
	MemberLoginResponseDto login(EmailAndPasswordLoginRequestDto dto);
}

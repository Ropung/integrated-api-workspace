package com.joara.auth.usecase;

import com.joara.auth.usecase.dto.MemberLoginDto.MemberLoginRequestDto;
import com.joara.auth.usecase.dto.MemberLoginDto.MemberLoginResponseDto;

public interface LoginUseCase {
	MemberLoginResponseDto login(MemberLoginRequestDto dto);
}

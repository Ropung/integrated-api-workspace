package com.joara.auth.usecase;

import com.joara.auth.domain.model.Member;
import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpRequestDto;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpResponseDto;

public interface SignUpUseCase {
//	boolean signUp(MemberSignUpRequestDto dto);
	MemberSignUpResponseDto signUp(MemberSignUpRequestDto dto, AccountStatus status, CertType certType);
	boolean signUp(Member member);
}

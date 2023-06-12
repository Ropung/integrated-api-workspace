package com.joara.auth.usecase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joara.auth.domain.model.type.Gender;
import lombok.Builder;

import javax.validation.constraints.Pattern;

public record MemberSignUpDto() {
	public record MemberSignUpRequestDto(
			String email,
			@JsonProperty("password")
			@Pattern(
					regexp =  "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
					message = "비밀번호 양식을 확인해주세요."
			)
			String rawPassword,
			String name,
			String nickname,
			String phone,
			String birth,
			Gender gender
	){}
	@Builder
	public record MemberSignUpResponseDto(
			boolean success
	){}
}

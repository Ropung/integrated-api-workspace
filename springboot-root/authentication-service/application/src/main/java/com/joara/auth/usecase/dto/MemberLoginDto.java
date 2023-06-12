package com.joara.auth.usecase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.Pattern;

public record MemberLoginDto() {
	public record MemberLoginRequestDto(
			String email,
			@JsonProperty("password")
			@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
			String rawPassword
	){}
	
	@Builder
	public record MemberLoginResponseDto(
			@JsonInclude(Include.NON_EMPTY)
			String token
	){}
}

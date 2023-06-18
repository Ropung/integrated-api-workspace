package com.joara.auth.repository.projection;

import com.joara.auth.domain.model.type.Gender;
import com.joara.auth.domain.model.type.MemberTier;

import java.util.UUID;

public record MemberQueryProjection() {
	public record MemberIdProjection(
			UUID id
	) {}
	
	public record MemberEmailNicknameProjection(
			String email,
			String nickname
	) {
	}

	public record MemberProfileProjection(
			String email,
			String name,
			String nickname,
			String phone,
			Gender gender,
			String birth,
			MemberTier tier
	) {}
}

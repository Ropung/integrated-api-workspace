package com.joara.member;

import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.Gender;
import com.joara.auth.domain.model.type.MemberTier;

import java.util.UUID;

public record MemberReadModels() {
	public record DefaultMemberReadModel(
			UUID id,
			String email,
			String name,
			String nickname,
			String phone,
			Gender gender,
			String birth,
			AccountStatus status,
			String tier
	) {}

	public record MemberProfileReadModel(
			String email,
			String name,
			String nickname,
			String phone,
			Gender gender,
			String birth,
			MemberTier tier
	) {}

	public record MemberIdReadModel(UUID id) {}
}

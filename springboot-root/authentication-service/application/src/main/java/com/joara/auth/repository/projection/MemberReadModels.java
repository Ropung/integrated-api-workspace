package com.joara.auth.repository.projection;

import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.Gender;

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
			AccountStatus status
	) {}
}

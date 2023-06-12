package com.joara.auth.repository.projection;

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
}

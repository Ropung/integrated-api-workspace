package com.joara.auth.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MemberRefreshToken {
	public UUID id;
	private UUID memberId;
	private String refreshToken;
	private OffsetDateTime createdAt;
	private OffsetDateTime deletedAt;
}
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
public class MemberFavorGenre{
	public UUID id;
	public UUID genreId;
	public UUID memberId;
	public String favorGenreName;
	public OffsetDateTime createdAt;
}
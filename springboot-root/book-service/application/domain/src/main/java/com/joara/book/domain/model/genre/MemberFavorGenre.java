package com.joara.book.domain.model.genre;

import com.joara.util.time.ServerTime;
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
	public Long genreId;
	public UUID memberId;
	public String nickname;
	@Builder.Default
	public OffsetDateTime createdAt = ServerTime.now();
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}
package com.joara.book.domain.model.episode;

import com.joara.book.domain.model.episode.type.EpisodeStatus;
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
public class EpisodeComment {
	public UUID id;
	public UUID epiId;
	public String memberId;
	public String nickname;
	public String content;
	public EpisodeStatus status;
	public OffsetDateTime createdAt;
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
	
	
}
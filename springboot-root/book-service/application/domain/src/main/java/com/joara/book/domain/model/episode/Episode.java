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
public class Episode {
	public UUID id;
	public Long bookId;
	public Long epiNum;
	public UUID memberId;
	public String bookTitle;
	public String nickname;
	public String epiTitle;
	public String content;
	public String quote;
	public String coverUrl;
	public Long heartCount;
	public Long viewCount;
	public Long commentCount;
	public EpisodeStatus status;
	public OffsetDateTime createdAt;
	public OffsetDateTime updatedAt;
	public OffsetDateTime deletedAt;
}

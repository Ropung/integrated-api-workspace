package com.joara.book.domain.model.episode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class EpisodeHeart{
	public UUID id;
	public Long bookId; //추가됨
	public UUID memberId;
	public UUID epiId;
	public String nickname;
}

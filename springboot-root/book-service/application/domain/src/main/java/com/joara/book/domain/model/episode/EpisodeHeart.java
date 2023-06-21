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
public class EpisodeHeart{ // Love -> Heart
	public UUID id;
	public UUID memberId;
	public UUID epiId;
	public String nickname;
//	public Boolean isEpi; // 삭제

}

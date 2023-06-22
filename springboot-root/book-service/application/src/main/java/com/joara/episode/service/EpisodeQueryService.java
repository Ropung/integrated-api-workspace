package com.joara.episode.service;

import com.joara.book.domain.model.book.type.SearchType;
import com.joara.episode.usecase.EpisodeReadUseCase;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EpisodeQueryService implements EpisodeReadUseCase {
	@Override
	public EpisodeListResponseDto findEpisodesByBookId(
			Long bid, Pageable pageable, SearchType searchType, String keyword) {

		return null;
	}
}

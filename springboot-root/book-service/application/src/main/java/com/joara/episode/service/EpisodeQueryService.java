package com.joara.episode.service;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.exception.BookErrorCode;
import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import com.joara.episode.repository.EpisodeQueryRepository;
import com.joara.episode.usecase.EpisodeReadUseCase;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EpisodeQueryService implements EpisodeReadUseCase {

	private final EpisodeQueryRepository episodeQueryRepository;

	@Override
	public EpisodeListResponseDto findEpisodesByBookId(
			Long bookId, Pageable pageable) {

		Page<EpisodeListViewReadModel> episodeSearchResult = episodeQueryRepository.findAllByBookId(bookId, pageable);

		long lastPageNumber = episodeSearchResult.getTotalPages();
		if (pageable.getPageNumber() >= lastPageNumber) {
			throw BookErrorCode.PAGE_OUT_OF_RANGE.defaultException();
		}

		List<EpisodeListViewReadModel> episodeList = episodeSearchResult.getContent();

		if (episodeList.isEmpty()) {
			throw BookErrorCode.BOOK_NOT_FOUND.defaultException();
		}

		return EpisodeListResponseDto.builder()
				.episodeList(episodeList)
				.lastPage(lastPageNumber)
				.build();
	}

	@Override
	public EpisodeViewResponseDto findEpisodeByID(UUID eid) {
		Episode episodeOptional = episodeQueryRepository.findById(eid)
				.orElseThrow(BookErrorCode.EPISODE_NOT_FOUND::defaultException);

		String bookTitle = episodeOptional.bookTitle;
		String epiTitle = episodeOptional.epiTitle;
		String content = episodeOptional.content;

		return EpisodeViewResponseDto.builder()
				.bookTitle(bookTitle)
				.epiTitle(epiTitle)
				.content(content)
				.build();
	}
}

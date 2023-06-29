package com.joara.episode.service;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import com.joara.episode.repository.EpisodeQueryRepository;
import com.joara.episode.usecase.EpisodeReadUseCase;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeViewResponseDto;
import com.joara.exception.status2xx.NoContentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EpisodeQueryService implements EpisodeReadUseCase {

	private final EpisodeQueryRepository episodeQueryRepository;

	@Override
	public EpisodeListResponseDto findEpisodesByBookId(
			Long bookId, Pageable pageable) {
		List<EpisodeStatus> readableStatusList = EpisodeStatus.readableItems();
		Page<EpisodeListViewReadModel> episodeSearchResult = episodeQueryRepository.findAllByBookIdAndStatusInReadableStatus(
				bookId,
				readableStatusList,
				pageable
		);

		long lastPageNumber = episodeSearchResult.getTotalPages();
		if (pageable.getPageNumber() > lastPageNumber) {
			throw BookErrorCode.PAGE_OUT_OF_RANGE.defaultException();
		}

		List<EpisodeListViewReadModel> episodeList = episodeSearchResult.getContent();

		if (episodeList.isEmpty()) {
			throw new NoContentException();
		}

		return EpisodeListResponseDto.builder()
				.episodeList(episodeList)
				.lastPage(lastPageNumber)
				.build();
	}

	@Override
	public EpisodeViewResponseDto findEpisodeByEpiNum(Long bookId, Long epiNum) {
		Episode episodeOptional = episodeQueryRepository.findByBookIdAndEpiNum(bookId, epiNum)
				.orElseThrow(BookErrorCode.EPISODE_NOT_FOUND::defaultException);

		String bookTitle = episodeOptional.bookTitle;
		System.out.println(bookTitle);
		String epiTitle = episodeOptional.epiTitle;
		String content = episodeOptional.content;

		return EpisodeViewResponseDto.builder()
				.bookTitle(bookTitle)
				.epiTitle(epiTitle)
				.content(content)
				.build();
	}

	@Override
	public Optional<UUID> findMemberIdByEpisodeId(UUID episodeId) {
		return episodeQueryRepository
				.findById(episodeId)
				.map((episode) -> {
					return episode.memberId;
				}); // map to member id
	}
}

package com.joara.episode.service;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookCommandRepository;
import com.joara.book.repository.BookQueryRepository;
import com.joara.clients.MemberQueryPort;
import com.joara.episode.repository.EpisodeCommandRepository;
import com.joara.episode.repository.EpisodeQueryRepository;
import com.joara.episode.usecase.EpisodeCreateUseCase;
import com.joara.episode.usecase.EpisodeDeleteUseCase;
import com.joara.episode.usecase.EpisodeUpdateUseCase;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateRequestDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateResponseDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeDeleteResponseDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeUpdateRequestDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeUpdateResponseDto;
import com.joara.episode.usecase.mapper.EpisodeDtoMapper;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.upload.service.UploadImageService;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EpisodeCommandService implements EpisodeCreateUseCase, EpisodeUpdateUseCase, EpisodeDeleteUseCase {

	private final EpisodeCommandRepository episodeCommandRepository;
	private final EpisodeQueryRepository episodeQueryRepository;
	private final BookQueryRepository bookQueryRepository;
	private final BookCommandRepository bookCommandRepository;
	private final MemberQueryPort memberQueryPort;
	private final EpisodeDtoMapper mapper;
	private final UploadImageService uploadImageService;
	private final JwtParser jwtParser;


	@Override
	public EpisodeCreateResponseDto create(Long bookId, EpisodeCreateRequestDto dto, MultipartFile file, HttpServletRequest request) {
		Episode episode = mapper.from(dto); // dto -> domain

		return EpisodeCreateResponseDto.builder()
				.success(create(bookId, episode, file, request))
				.build();
	}

	@Override
	public boolean create(Long bookId, Episode episode, MultipartFile file, HttpServletRequest request) {
		JwtPayloadParser parser = jwtParser.withRequest(request);
		String email = parser.subject();
		String nickname = parser.claims()
				.get("nickname", String.class);

		String coverUrl =  null;
		String middlePath = "books";
		if(file != null) {
			try {
				coverUrl = uploadImageService.upload(file, middlePath);
			} catch (IOException e) {
				throw BookErrorCode.IMAGE_UPLOAD_FAILURE.defaultException(e);
			}
		}

		episode.bookId = bookId;
		episode.bookTitle = bookQueryRepository.findTitleByBookId(bookId);
		episode.memberId = memberQueryPort.findIdByEmail(email)
				.orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
				.id();
		episode.nickname = nickname;
		episode.cover = coverUrl;
		episode.status = EpisodeStatus.ACTIVE;
		episode.viewCount = 0L;
		episode.heartCount = 0L;
		episode.createdAt = ServerTime.now(); // 디폴트인데 안들어가서 추가..
		episodeCommandRepository.save(episode);
		return true;
	}

	@Override
	public EpisodeUpdateResponseDto update(Long bid, UUID eid, EpisodeUpdateRequestDto dto, HttpServletRequest request) {

		//jwt 토큰 가져와서 유효성 작업
		JwtPayloadParser parser = jwtParser.withRequest(request);
		String nickname = parser.claims()
				.get("nickname", String.class);
		boolean isNickname = bookCommandRepository.existsNicknameByNickname(nickname);
		if(!isNickname) throw BookErrorCode.FORBIDDEN.defaultException();

		// 책에 에피소드 존재 여부 확인
		boolean isBook = episodeQueryRepository.existsByIdAndBookId(bid, eid);
		if (!isBook) throw BookErrorCode.BOOK_NOT_FOUND.defaultException();

		// 에피소드 존재 여부
		boolean isEpi = episodeQueryRepository.existsById(eid);
		if(!isEpi) throw BookErrorCode.EPISODE_NOT_FOUND.defaultException();

		episodeCommandRepository.update(bid, eid, dto.epiTitle(), dto.content(), dto.quote());

		return EpisodeUpdateResponseDto.builder()
				.success(true)
				.build();
	}

	@Override
	public EpisodeDeleteResponseDto delete(Long bid, UUID eid) {
		// 책 존재 여부
		boolean isBook = bookQueryRepository.existsById(bid);
		if(!isBook) throw BookErrorCode.BOOK_NOT_FOUND.defaultException();

		// 에피소드 존재 여부
		boolean isEpi = episodeQueryRepository.existsById(eid);
		if(!isEpi) throw BookErrorCode.EPISODE_NOT_FOUND.defaultException();

		episodeCommandRepository.deleteById(eid);

		return EpisodeDeleteResponseDto.builder()
				.success(true)
				.build();
	}
}

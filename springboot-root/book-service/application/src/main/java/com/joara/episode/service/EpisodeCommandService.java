package com.joara.episode.service;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.clients.MemberQueryPort;
import com.joara.episode.repository.EpisodeCommandRepository;
import com.joara.episode.usecase.EpisodeCreateUseCase;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateRequestDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateResponseDto;
import com.joara.episode.usecase.mapper.EpisodeDtoMapper;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.upload.service.UploadImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class EpisodeCommandService implements EpisodeCreateUseCase {

	private final EpisodeCommandRepository episodeCommandRepository;
	private final EpisodeDtoMapper mapper;
	private final MemberQueryPort memberQueryPort;
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
		String coverUrl =  null;
		String middlePath = "books";
		if(file != null) {
			try {
				coverUrl = uploadImageService.upload(file, middlePath);
			} catch (IOException e) {
				throw BookErrorCode.IMAGE_UPLOAD_FAILURE.defaultException(e);
			}
		}

		JwtPayloadParser parser = jwtParser.withRequest(request);
		String email = parser.subject();
		String nickname = parser.claims()
				.get("nickname", String.class);

		episode.memberId = memberQueryPort.findIdByEmail(email)
				.orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
				.id();
		episode.nickname = nickname;
		episode.cover = coverUrl;
		episode.status = EpisodeStatus.ACTIVE;

		episodeCommandRepository.save(episode);
		return true;
	}
}

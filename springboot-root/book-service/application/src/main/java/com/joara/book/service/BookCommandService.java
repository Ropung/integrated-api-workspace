package com.joara.book.service;

import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookCommandRepository;
import com.joara.book.usecase.BookCreateUseCase;
import com.joara.book.usecase.BookUpdateUseCase;
import com.joara.book.usecase.BookRemoveUseCase;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateResponseDto;
import com.joara.book.usecase.dto.BookCommandDto.BookModifyRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookModifyResponseDto;
import com.joara.book.usecase.mapper.BookDtoMapper;
import com.joara.clients.MemberQueryPort;
import com.joara.episode.repository.EpisodeCommandRepository;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.upload.service.UploadImageService;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.OffsetDateTime;


@Service
@RequiredArgsConstructor
public class BookCommandService
		implements BookCreateUseCase, BookUpdateUseCase, BookRemoveUseCase {

	private final BookCommandRepository bookCommandRepository;
	private final EpisodeCommandRepository episodeCommandRepository;
	private final BookDtoMapper mapper;
	private final MemberQueryPort memberQueryPort;
	private final UploadImageService uploadImageService;
	private final JwtParser jwtParser;

	@Override
	public BookCreateResponseDto create(BookCreateRequestDto dto, MultipartFile file, BookStatus status, HttpServletRequest request) {
		OffsetDateTime now = ServerTime.now();
		Book book = mapper.from(dto, status, now);

		return BookCreateResponseDto.builder()
				.success(create(book, file, request))
				.build();
	}

	@Override
	public boolean create(Book book, MultipartFile file, HttpServletRequest request) {
		boolean isTitle = bookCommandRepository.existsBookByTitle(book.title);
		if (isTitle) throw BookErrorCode.DUPLICATED_BOOK_TITLE.defaultException();

		String coverUrl= null;
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

		book.memberId = memberQueryPort.findIdByEmail(email)
				.orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
				.id();
		book.nickname = nickname;
		book.coverUrl = coverUrl;
		book.score = 0.0;

		if (book.createdAt == null) {
			book.createdAt = ServerTime.now();
		}

		bookCommandRepository.save(book);
		return true;
	}

	@Override
	public BookModifyResponseDto modify(BookModifyRequestDto dto) {
		boolean result = bookCommandRepository.update(
				dto.title(),
				dto.genreIdList(),
				dto.description(),
				dto.status(),
				dto.bookId()
		);

		return BookModifyResponseDto.builder()
				.success(result)
				.build();
	}

	@Override
	public boolean modifyStatus(Long bookId, BookStatus status) {
		return bookCommandRepository.update(bookId, status);
	}

	@Override
	public boolean remove(Long bookId) {
		bookCommandRepository.updateStatusAndDeletedAt(bookId, BookStatus.REMOVED, ServerTime.now());
		episodeCommandRepository.updateAllStatusByIdAndInTargetStatusList(
				bookId,
				EpisodeStatus.DISABLED
		);
		return true;
	}
}

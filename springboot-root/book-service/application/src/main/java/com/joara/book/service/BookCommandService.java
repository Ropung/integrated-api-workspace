package com.joara.book.service;

import com.joara.book.domain.model.book.Book;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookCommandRepository;
import com.joara.book.usecase.BookCreateUseCase;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateResponseDto;
import com.joara.book.usecase.mapper.BookDtoMapper;
import com.joara.clients.MemberQueryPort;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.upload.service.UploadImageService;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class BookCommandService implements BookCreateUseCase {

	private final BookCommandRepository bookCommandRepository;
	private final BookDtoMapper mapper;
	private final UploadImageService uploadImageService;
	private final JwtParser jwtParser;
	private final MemberQueryPort memberQueryPort;

	@Override
	public boolean create(Book book, MultipartFile file, HttpServletRequest request) {
		boolean isTitle = bookCommandRepository.existsBookByTitle(book.title);
		if (isTitle) throw BookErrorCode.BOOK_NOT_FOUND.defaultException();

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
		book.memberNickname = nickname;
		book.coverUrl = coverUrl;
		if (book.createdAt == null) {
			book.createdAt = ServerTime.now();
		}
		bookCommandRepository.save(book);
		return true;
	}

	@Override
	public BookCreateResponseDto create(BookCreateRequestDto dto, MultipartFile file, HttpServletRequest request) {

		Book book = mapper.from(dto);

		return BookCreateResponseDto.builder()
				.success(create(book, file, request))
				.build();
	}
}

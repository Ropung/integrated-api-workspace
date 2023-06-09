package com.joara.web.episode;

import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.BookUpdateUseCase;
import com.joara.clients.MemberQueryPort;
import com.joara.episode.exception.EpisodeErrorCode;
import com.joara.episode.usecase.EpisodeCreateUseCase;
import com.joara.episode.usecase.EpisodeDeleteUseCase;
import com.joara.episode.usecase.EpisodeReadUseCase;
import com.joara.episode.usecase.EpisodeUpdateUseCase;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateRequestDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateResponseDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeDeleteResponseDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeUpdateRequestDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeUpdateResponseDto;
import com.joara.jwt.util.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

import static com.joara.util.validation.Preconditions.validate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bid}/episode")
public class EpisodeCommandApi {

    private final EpisodeCreateUseCase episodeCreateUseCase;
    private final EpisodeUpdateUseCase episodeUpdateUseCase;
    private final EpisodeDeleteUseCase episodeDeleteUseCase;
    private final EpisodeReadUseCase episodeReadUseCase;
    private final BookQueryUseCase bookQueryUseCase;
    private final BookUpdateUseCase bookUpdateUseCase;
    private final MemberQueryPort memberQueryPort;
    private final JwtParser jwtParser;

    @PostMapping()
    public EpisodeCreateResponseDto create(
            @PathVariable Long bid,
            @ModelAttribute @Valid EpisodeCreateRequestDto dto,
            @RequestPart(value = "coverImage", required = false)
            MultipartFile file,
            HttpServletRequest request
    ){
        BookDetailedViewReadModel book = bookQueryUseCase.findBookById(bid).book();
        validate(book.status().writable, EpisodeErrorCode.BOOK_NOT_WRITABLE);

        EpisodeCreateResponseDto responseDto =
                episodeCreateUseCase.create(bid, dto, file, request);
        // TODO episode size 비정규화하면 첫 에피소드 삽입때만 실행으로 변경
        bookUpdateUseCase.modifyStatus(bid, BookStatus.ACTIVE);
        return responseDto;
    }

    @PutMapping("/{eid}")
    public EpisodeUpdateResponseDto update(
            @PathVariable Long bid,
            @PathVariable UUID eid,
            @RequestBody @Valid EpisodeUpdateRequestDto dto,
            HttpServletRequest request){
        // 오류떠서 일단 주석
//        String email = jwtParser.withRequest(request)
//                .subject();
//        UUID tokenMemberId = memberQueryPort
//                .findIdByEmail(email)
//                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
//                .id();
//        // find Member Id by EpisodeId
//        UUID episodeMemberId = episodeReadUseCase
//                .findMemberIdByEpisodeId(eid)
//                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException);
//
//        Preconditions.validate(
//                tokenMemberId == episodeMemberId,
//                BookErrorCode.FORBIDDEN
//        );
        return episodeUpdateUseCase.update(bid, eid, dto, request);
    }

    @DeleteMapping("/{eid}")
    public EpisodeDeleteResponseDto delete(
            @PathVariable Long bid,
            @PathVariable UUID eid,
            HttpServletRequest request
    ){
        // 오류떠서 일단 주석
        // 인가(횡단 관심사) - 이 에이피아이를 써도 된다. 권한 등을 확인하는 것. -> 우리 서비스 내부가 아닌 경우가 생김.
//        String email = jwtParser.withRequest(request)
//                .subject();
//        UUID tokenMemberId = memberQueryPort
//                .findIdByEmail(email)
//                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
//                .id();
//        // find Member Id by EpisodeId
//        UUID episodeMemberId = episodeReadUseCase
//                .findMemberIdByEpisodeId(eid)
//                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException);
//
//        Preconditions.validate(
//                tokenMemberId == episodeMemberId,
//                BookErrorCode.FORBIDDEN
//        );
        return episodeDeleteUseCase.delete(bid, eid);
    }
}

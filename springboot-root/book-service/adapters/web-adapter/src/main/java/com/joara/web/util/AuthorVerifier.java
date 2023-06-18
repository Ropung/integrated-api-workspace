package com.joara.web.util;

import com.joara.book.domain.model.book.Book;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.clients.MemberQueryPort;
import com.joara.jwt.util.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public final class AuthorVerifier {
    private final BookQueryUseCase bookQueryUseCase;
    private final MemberQueryPort memberQueryPort;

    private final JwtParser jwtParser;

    public boolean verify(HttpServletRequest userRequest, Long bookId) {
        String email = jwtParser.withRequest(userRequest).subject(); // member id (X), email (O)

        // email -> memberId
        UUID memberId = memberQueryPort
                .findIdByEmail(email)
                .orElseThrow(BookErrorCode.FORBIDDEN::defaultException)
                .id();

        // body.bookId -> BOOK 조회
        Book book = bookQueryUseCase
                .findById(bookId)
                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);

        return Objects.equals(memberId, book.memberId);
    }
}

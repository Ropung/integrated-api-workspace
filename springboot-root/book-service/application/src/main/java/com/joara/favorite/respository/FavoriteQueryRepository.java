package com.joara.favorite.respository;

import com.joara.book.domain.model.book.MemberFavorBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FavoriteQueryRepository {
    Page<MemberFavorBook> findByMemberId(UUID memberId, Pageable pageable);

    UUID findByBookIdAndMemberId(Long bookId, UUID memberId);
}

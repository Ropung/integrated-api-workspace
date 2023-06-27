package com.joara.favorite.respository;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.support.repository.BaseCommandRepository;

import java.util.Optional;
import java.util.UUID;

public interface FavoriteCommandRepository extends BaseCommandRepository<MemberFavorBook, UUID> {
}

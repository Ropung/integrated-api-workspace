package com.joara.clients;

import java.util.List;

public interface RecommendedBookQueryPort {
    List<Long> findRecommendedBookIdsByBookId(Long bookId);
}

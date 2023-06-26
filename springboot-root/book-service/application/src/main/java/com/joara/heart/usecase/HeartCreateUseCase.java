package com.joara.heart.usecase;

import com.joara.book.domain.model.episode.EpisodeHeart;
import com.joara.heart.usecase.dto.HeartCommandDto.HeartCreateRequestDto;
import com.joara.heart.usecase.dto.HeartCommandDto.HeartCreateResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface HeartCreateUseCase {

    HeartCreateResponseDto create(Long bookId, UUID epiId, HttpServletRequest request);
    boolean create(EpisodeHeart episodeHeart, HttpServletRequest request);
}

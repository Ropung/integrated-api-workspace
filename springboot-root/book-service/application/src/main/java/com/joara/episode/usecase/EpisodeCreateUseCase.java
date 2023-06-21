package com.joara.episode.usecase;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateRequestDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface EpisodeCreateUseCase {
    EpisodeCreateResponseDto create(Long bookId, EpisodeCreateRequestDto dto, MultipartFile file, HttpServletRequest request);

    boolean create(Long bookId, Episode episode, MultipartFile file, HttpServletRequest request);

}

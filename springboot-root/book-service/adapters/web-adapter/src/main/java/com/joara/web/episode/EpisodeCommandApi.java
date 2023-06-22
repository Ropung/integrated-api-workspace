package com.joara.web.episode;

import com.joara.episode.usecase.EpisodeCreateUseCase;
import com.joara.episode.usecase.EpisodeDeleteUseCase;
import com.joara.episode.usecase.dto.EpisodeCommandDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateResponseDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeDeleteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class EpisodeCommandApi {

    private final EpisodeCreateUseCase episodeCreateUseCase;
    private final EpisodeDeleteUseCase episodeDeleteUseCase;

    @PostMapping("/{bookId}/episode")
    public EpisodeCreateResponseDto create(
            @PathVariable Long bookId,
            @ModelAttribute @Valid EpisodeCommandDto.EpisodeCreateRequestDto dto,
            @RequestPart(value = "coverImage", required = false)
            MultipartFile file,
            HttpServletRequest request
    ){
        return episodeCreateUseCase.create(bookId, dto, file, request);
    }

    @DeleteMapping("/{bid}/episode/{eid}")
    public EpisodeDeleteResponseDto delete(@PathVariable Long bid, @PathVariable UUID eid){
        return episodeDeleteUseCase.delete(bid, eid);
    }


}

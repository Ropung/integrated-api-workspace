package com.joara.episode.repository.Projection;

public record EpisodeProjections() {
    // TODO 일단 리스트로 다 받음 & Read-Model도 만들어야함
    public record DefaultEpisodeProjection(
        String bookTitle,
        String nickname,
        String epiTitle,
        String content,
        String viewCount,
        String hearCount,
        String CommentCount,
        String createdAT

    ){}
}

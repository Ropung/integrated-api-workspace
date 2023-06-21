package com.joara.book.repository.Projection;

public record BookProjections() {
    public record DefaultBookProjection(
            String nickname,
            String title,
            String coverUrl

    ){}
}

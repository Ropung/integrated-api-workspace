-- 게시판 대댓글(BoardReply)
CREATE TABLE IF NOT EXISTS joara_basic.book_genre_map (
    id                  UUID                 PRIMARY KEY     DEFAULT uuid_generate_v4(),
    book_id             BIGINT               NOT NULL,
    genre_id            BIGINT               NOT NULL,

    -- 복합 유니크
    CONSTRAINT uq_book_genre_mapping UNIQUE(book_id, genre_id)
);

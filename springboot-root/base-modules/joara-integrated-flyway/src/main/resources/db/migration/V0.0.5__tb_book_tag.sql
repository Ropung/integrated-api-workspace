--작품태그 목록(BookTag)테이블
CREATE TABLE IF NOT EXISTS joara_basic.book_tag (
    id                  UUID                                PRIMARY KEY,
    book_id             BIGINT                              NOT NULL,
    tag_name            VARCHAR(255)
);
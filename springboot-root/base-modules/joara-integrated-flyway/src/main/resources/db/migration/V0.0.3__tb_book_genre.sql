--작품(BookGenre)장르 테이블
CREATE TABLE IF NOT EXISTS joara_basic.book_genre (
    id                  BIGINT                              PRIMARY KEY,
    genre_kor           VARCHAR(255)                        UNIQUE,
    genre_eng           VARCHAR(255)                        UNIQUE
);

--작품(BookGenre)장르 테이블
CREATE TABLE IF NOT EXISTS joara_basic.book_genre (
    id                  BIGSERIAL                           PRIMARY KEY,
    genre_kor           VARCHAR(255)                        ,
    genre_eng           VARCHAR(255),

    CONSTRAINT uq_genre_kor UNIQUE(genre_kor),
    CONSTRAINT uq_genre_eng UNIQUE(genre_eng)
);

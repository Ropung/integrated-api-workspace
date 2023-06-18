-- 작품(Book)테이블,  SEQUENCE 추가
CREATE TABLE IF NOT EXISTS joara_basic.book (
    id                  BIGINT                              PRIMARY KEY,
    nickname            VARCHAR(255)                        ,
    genre_id            BIGINT                              ,
    book_title          VARCHAR(255)                        UNIQUE,
    description         TEXT                                ,
    cover_url           VARCHAR(255)                        ,
    status              VARCHAR(255)                        ,
    total_view_count    BIGINT                              ,
    total_heart_count   BIGINT                              ,
    favor_count         BIGINT                              ,
    favor_on_off        VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);


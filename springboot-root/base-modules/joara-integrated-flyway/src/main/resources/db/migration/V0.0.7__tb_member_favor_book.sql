-- 회원 선호 작품 테이블
CREATE TABLE IF NOT EXISTS joara_basic.member_favor_book(
    id                  UUID                                PRIMARY KEY,
    book_id             BIGINT                              NOT NULL,
    genre_id            BIGINT                              NOT NULL,
    member_id           UUID                                NOT NULL,
    nickname            VARCHAR(255)                        NOT NULL,
    book_title          VARCHAR(255)                        NOT NULL,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);
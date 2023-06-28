-- 회원 선호 작품 테이블
CREATE TABLE IF NOT EXISTS joara_basic.member_favor_book(
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    book_id             BIGINT                              NOT NULL,
    genre_id            BIGINT                              ,
    member_id           UUID                                NOT NULL,
    nickname            VARCHAR(255)                        NOT NULL,
    book_title          VARCHAR(255)                        ,
    created_at          DATE                                ,
    updated_at          DATE                                ,
    deleted_at          DATE
);
-- 회원 선호 장르 테이블
CREATE TABLE IF NOT EXISTS joara_basic.member_favor_genre (
    id                  UUID                                PRIMARY KEY,
    genre_id            BIGINT                              NOT NULL,
    nickname            VARCHAR(255)                        NOT NULL,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);

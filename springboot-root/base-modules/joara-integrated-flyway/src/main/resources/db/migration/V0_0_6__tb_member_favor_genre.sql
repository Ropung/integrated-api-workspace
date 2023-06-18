-- 회원 선호 장르 테이블
CREATE TABLE IF NOT EXISTS joara_basic.member_favor_genre (
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    genre_id            BIGINT                              NOT NULL,
    member_id           UUID                                NOT NULL,
    nickname            VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);

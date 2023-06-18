-- 작품(Book)테이블,  SEQUENCE 추가
CREATE TABLE IF NOT EXISTS joara_basic.book (
    id                  BIGSERIAL                           PRIMARY KEY,
    member_id           UUID                                ,
    genre_id            BIGINT                              ,
    nickname            VARCHAR(255)                        ,
    title               VARCHAR(255)                        ,
    description         TEXT                                ,
    cover_url           VARCHAR(255)                        ,
    status              VARCHAR(255)                        ,
    total_view_count    BIGINT                              ,
    total_heart_count   BIGINT                              ,
    favor_count         BIGINT                              ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE,

    -- 복합 유니크(두 컬럼 모두 일치하는 경우에 대한 유니크), 다른 작가랑은 작품 이름 겹쳐도 됨.
    CONSTRAINT uq_book_memid_title UNIQUE(member_id, title)
);

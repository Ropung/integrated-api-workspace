
--회차좋아요(EpiLove)
CREATE TABLE IF NOT EXISTS joara_basic.epi_love (
    id                  UUID                                PRIMARY KEY,
    member_id           UUID                                ,
    epi_id              UUID                                NOT NULL,
    is_epi              BOOLEAN                             NOT NULL DEFAULT false
);



--회차(Epi)테이블,  SEQUENCE 추가
CREATE TABLE IF NOT EXISTS joara_basic.epi (
    id                  UUID                                PRIMARY KEY,
    book_id             UUID                                NOT NULL,
    member_id           UUID                                ,
    nickname            VARCHAR(255)                        ,
    title               VARCHAR(255)                        ,
    content             TEXT                                ,
    review              VARCHAR(255)                        ,
    cover_url           VARCHAR(255)                        ,
    view_count          BIGINT                              DEFAULT 0,
    status              VARCHAR(255)                        DEFAULT 'PENDING',
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);


--회차(EpiComment)댓글 테이블,  SEQUENCE 추가

CREATE TABLE IF NOT EXISTS joara_basic.epi_reply (
    id                   UUID                                PRIMARY KEY,
    epi_id               UUID                                NOT NULL,
    epi_reply_content    VARCHAR(255)                        NOT NULL,
    created_at           DATE                                NOT NULL,
    updated_at           DATE                                ,
    deleted_at           DATE
);

--회차(EpiReply)댓글 테이블,  SEQUENCE 추가
CREATE TABLE IF NOT EXISTS joara_basic.epi_sub_reply (
    id                      UUID                                PRIMARY KEY,
    epi_sub_reply_id        UUID                                NOT NULL,
    epi_sub_reply_content   VARCHAR(255)                        NOT NULL,
    created_at              DATE                                NOT NULL,
    updated_at              DATE                                ,
    deleted_at              DATE
);

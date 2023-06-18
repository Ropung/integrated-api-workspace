-- 회차 Episode
CREATE TABLE IF NOT EXISTS joara_basic.episode (
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    book_id             BIGINT                              NOT NULL,
    member_id           UUID                                NOT NULL,
    book_title          VARCHAR(255)                        ,
    nickname            VARCHAR(255)                        ,
    epi_title           VARCHAR(255)                        ,
    content             TEXT                                ,
    quote               VARCHAR(255)                        ,
    cover               VARCHAR(255)                        ,
    view_count          BIGINT                              DEFAULT 0,
    heart_count         BIGINT                              DEFAULT 0,
    comment_count       BIGINT                              DEFAULT 0,
    status              VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);

COMMENT ON COLUMN joara_basic.episode.quote IS '작가의 한 마디(회차)';
-- 회차 Episode
CREATE TABLE IF NOT EXISTS joara_basic.episode (
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    book_id             BIGINT                              NOT NULL,
    nickname            VARCHAR(255)                        NOT NULL,
    epi_title           VARCHAR(255)                        NOT NULL,
    content             TEXT                                NOT NULL,
    quote               VARCHAR(255)                        ,
    cover               VARCHAR(255)                        ,
    view_count          BIGINT                              ,             -- default 0
    heart_count         BIGINT                              NOT NULL,     -- default 0
    comment_count       BIGINT                              NOT NULL,     -- default 0
    status              VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);









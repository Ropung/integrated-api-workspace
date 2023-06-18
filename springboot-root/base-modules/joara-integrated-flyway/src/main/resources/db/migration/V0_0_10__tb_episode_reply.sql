--회차 대댓글 Episode Reply
CREATE TABLE IF NOT EXISTS joara_basic.episode_reply (
    id                      UUID                            PRIMARY KEY     DEFAULT uuid_generate_v4(),
    comment_id              UUID                            NOT NULL,
    member_id               UUID                            NOT NULL,
    nickname                VARCHAR(255)                    ,
    content                 VARCHAR(255)                    ,
    status                  VARCHAR(255)                    ,                -- default 'ACTIVE'
    created_at              DATE                            NOT NULL,        -- default sysdate
    updated_at              DATE                            ,
    deleted_at              DATE
);
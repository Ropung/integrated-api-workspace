-- 회차 댓글 Episode Comment
CREATE TABLE IF NOT EXISTS joara_basic.episode_comment (
    id                   UUID                               PRIMARY KEY     DEFAULT uuid_generate_v4(),
    epi_id               UUID                               NOT NULL,
    member_id            UUID                                NOT NULL,
    nickname             VARCHAR(255)                       ,
    content              VARCHAR(255)                       ,
    status               VARCHAR(255)                       ,                -- default 'ACTIVE'
    created_at           DATE                               NOT NULL,
    updated_at           DATE                               ,
    deleted_at           DATE
);
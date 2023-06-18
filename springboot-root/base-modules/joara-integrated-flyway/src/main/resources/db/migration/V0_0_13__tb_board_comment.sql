-- 게시판 댓글(BoardComment)
CREATE TABLE IF NOT EXISTS joara_basic.board_comment (
    id                  UUID                 PRIMARY KEY     DEFAULT uuid_generate_v4(),
    board_id            UUID                 NOT NULL,
    member_id           UUID                 NOT NULL,
    nickname            VARCHAR(255)         ,
    content             VARCHAR(255)         ,
    status              VARCHAR(255)         ,
    created_at          DATE                 NOT NULL ,
    updated_at          DATE                 ,
    deleted_at          DATE
);

-- 게시판 대댓글(BoardReply)
CREATE TABLE IF NOT EXISTS joara_basic.board_reply (
    id                  UUID                 PRIMARY KEY     DEFAULT uuid_generate_v4(),
    board_comment_id    UUID                 NOT NULL,
    nickname            VARCHAR(255)         NOT NULL,
    content             VARCHAR(255)         NOT NULL,
    status              VARCHAR(255)         ,
    created_at          DATE                 NOT NULL ,
    updated_at          DATE                 ,
    deleted_at          DATE
);

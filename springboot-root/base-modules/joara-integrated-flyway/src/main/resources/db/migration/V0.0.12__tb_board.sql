-- board_num SEQUENCE
CREATE SEQUENCE IF NOT EXISTS board_sequence START 1;

-- 게시판(Board)
CREATE TABLE IF NOT EXISTS joara_basic.board (
    id                  UUID                PRIMARY KEY        DEFAULT uuid_generate_v4(),
    nickname            VARCHAR(255)        ,
    board_num           BIGSERIAL           NOT NULL,
    title               VARCHAR(255)        ,
    content             TEXT                ,
    status              VARCHAR(255)        ,
    created_at          DATE                NOT NULL,
    updated_at          DATE                ,
    deleted_at          DATE
)

-- ALTER TABLE joara_basic.board ALTER COLUMN board_num SET DEFAULT nextval('board_sequence');



-- V0.1.1 회원(Member) 테이블
CREATE TABLE IF NOT EXISTS joara_basic.member (
    id                  UUID                                PRIMARY KEY     DEFAULT uuid_generate_v4(),
    email               VARCHAR(255)                        NOT NULL,
    password            VARCHAR(255)                        NOT NULL,
    "name"              VARCHAR(255)                        ,
    nickname            VARCHAR(255)                        ,
    phone               VARCHAR(255)                        ,
    gender              CHAR(1)                             ,
    birth               VARCHAR(255)                        ,
    status              VARCHAR(255)                        ,
    tier                VARCHAR(255)                        ,
    certificated_by     VARCHAR(255)                        ,
    oauth_serial        BIGINT                              ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE,

    CONSTRAINT uq_member_nickname UNIQUE(nickname),
    CONSTRAINT uq_member_email UNIQUE(email)
);

COMMENT ON COLUMN joara_basic."member".certificated_by IS '인증처';
COMMENT ON COLUMN joara_basic."member".oauth_serial IS 'OAUTH 일련번호';

CREATE UNIQUE INDEX udx_member_nickname ON joara_basic.member(nickname);

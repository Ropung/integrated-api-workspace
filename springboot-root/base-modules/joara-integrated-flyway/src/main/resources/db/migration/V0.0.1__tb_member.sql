--토큰(Token)테이블
CREATE TABLE IF NOT EXISTS joara_basic.refresh_token (
    id                  UUID                                PRIMARY KEY,
    member_id           UUID                                NOT NULL,
    refresh_token       VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE
);

--회원(Member)테이블
CREATE TABLE IF NOT EXISTS joara_basic.member (
    id                  UUID                                PRIMARY KEY,
    email               VARCHAR(255)                        NOT NULL        UNIQUE,
    password            VARCHAR(255)                        NOT NULL,
    "name"              VARCHAR(255)                        NOT NULL,
    nickname            VARCHAR(255)                        NOT NULL        UNIQUE,
    phone               VARCHAR(255)                        ,
    gender              CHAR(1)                             ,
    birth               VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL,
    updated_at          DATE                                ,
    deleted_at          DATE                                ,
    status              VARCHAR(255)                        ,
    certificated_by     VARCHAR(255)                        ,
    oauth_serial        BIGINT
);

COMMENT ON COLUMN joara_basic."member".certificated_by IS '인증처';
COMMENT ON COLUMN joara_basic."member".oauth_serial IS 'OAUTH 일련번호';

CREATE UNIQUE INDEX udx_member_email ON joara_basic.member(email);


--회원선호장르(MemberFavorGenre)테이블
CREATE TABLE IF NOT EXISTS joara_basic.member_favor_genre (
    id                  UUID                                PRIMARY KEY,
    genre_id            UUID                                NOT NULL,
    member_id           UUID                                NOT NULL,
    favor_genre_name    VARCHAR(255)                        ,
    created_at          DATE                                NOT NULL
);

CREATE TABLE IF NOT EXISTS joara_basic.member_favor_book (
    id                  UUID                                PRIMARY KEY,
    member_id           UUID                                NOT NULL,
    book_id             UUID                                NOT NULL,
    favor_book_name     VARCHAR(255)                        NOT NULL,
    created_at          DATE                                NOT NULL
);


----유저 디폴트 아이디 생성
--INSERT INTO joara_basic.member(id,email, password,name, nickname,phone,gender,birth)
--VALUES ('62e9d74f-9524-4c14-92cb-0d2e47b86c01','email1','password1','name1','nickname1','phone1','M','birth1');
--INSERT INTO joara_basic.member(id,email, password,name, nickname,phone,gender,birth)
--VALUES ('a9a53fb2-3143-4b37-8e53-81124fc37202','email2','password1','name1','nickname2','phone1','M','birth1');
--INSERT INTO joara_basic.member(id,email, password,name, nickname,phone,gender,birth)
--VALUES ('c95f85eb-9c10-4851-bd6e-fc6c2f6db503','email3','password1','name1','nickname3','phone1','M','birth1');
--